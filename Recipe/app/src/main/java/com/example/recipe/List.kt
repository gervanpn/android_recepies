package com.example.recipe

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.databinding.FragmentListBinding
import com.example.recipe.model.RecipesViewModel
import com.google.firebase.auth.FirebaseAuth

class List : Fragment() {

    private val recipesListAdapter = RecipeAdapter(arrayListOf())
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: RecipesViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        firebaseAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            setHasOptionsMenu(true)
        }else {
            setHasOptionsMenu(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.apply {
            adapter = recipesListAdapter
        }
        observerViewModel()
        return binding.root
    }

    fun observerViewModel(){
        viewModel.recipesLiveData.observe(viewLifecycleOwner, Observer {recipes ->
            recipes.let {
                recipesListAdapter.updateRecipe(recipes)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView(). findNavController())
                || super.onOptionsItemSelected(item)
    }
}
