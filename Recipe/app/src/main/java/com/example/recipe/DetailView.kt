package com.example.recipe

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.databinding.FragmentDetailViewBinding
import com.google.android.material.snackbar.Snackbar



class DetailView : Fragment() {

    private lateinit var binding: FragmentDetailViewBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.readFireStorData()
        binding.imageView.clipToOutline = true
        return binding.root
    }
   private fun observerViewModel(){
        detailViewModel.recipesDetail.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let {
                binding.title?.text = recipes[recipes.indexOf(1)].recipeTitel.toString()

                Log.i("did", recipes[recipes.indexOf(1)].recipeTitel.toString())
            }
        })
    }

}