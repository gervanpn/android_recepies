package com.example.recipe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.databinding.FragmentListBinding

class List : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var recipeList: String =""
       viewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

        viewModel.readFireStorData()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = RecipeAdapter(viewModel.inputName.observe(viewLifecycleOwner,
            Observer {
            }))


        return binding.root


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