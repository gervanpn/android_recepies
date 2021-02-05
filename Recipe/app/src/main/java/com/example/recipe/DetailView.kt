package com.example.recipe

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipe.databinding.FragmentDetailViewBinding
import com.example.recipe.model.Recipe
import com.example.recipe.util.DownloadImage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class DetailView : Fragment() {

    private lateinit var binding: FragmentDetailViewBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            setHasOptionsMenu(true)
        } else {
            setHasOptionsMenu(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_page_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.update) {
            Snackbar.make(requireView(), "Under construction", Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view, container, false)
        var bidingViewHeader = binding.detailHeaderView ?: binding.detailHeaderViewLand
        var bidingViewDescriptionBinding =
            binding.detailViewDescription ?: binding.detailViewDescriptionLand
        val recipeTitle = bidingViewHeader?.recipeName
        val recipeDescription = bidingViewDescriptionBinding?.recipeDescriptionBody
        if (arguments == null) arguments = Recipe.recipeSelected
        val recipeName = arguments?.getString("name")
        val recipeImage = arguments?.getString("picture")
        val recipeInstruction = arguments?.getString("instruction")
        DownloadImage(binding.imageView).execute(recipeImage)
        recipeTitle?.setText(recipeName)
        recipeDescription?.setText(recipeInstruction)
        binding.imageView?.clipToOutline = true
        return binding.root
    }

}