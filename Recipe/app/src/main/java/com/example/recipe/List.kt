package com.example.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.databinding.FragmentListBinding
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipesViewModel

class List<T> : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentListBinding
    private lateinit var recipesVM: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        recipesVM = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
        recipesVM.recipesLiveData.observe(viewLifecycleOwner, {
            binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerView.adapter = RecipeAdapter()
        })
        return binding.root

    }

    private val userListUpdateObserver: Observer<ArrayList<Recipe>> =
        object : Observer<ArrayList<Recipe?>?> {
            fun onChanged(userArrayList: ArrayList<Recipe?>?) {
                homeAdapter = HomeAdapter(requireActivity(), userArrayList)
                recyclerView.setLayoutManager(LinearLayoutManager(requireActivity()))
                recyclerView.setAdapter(homeAdapter)
            }
        }


}