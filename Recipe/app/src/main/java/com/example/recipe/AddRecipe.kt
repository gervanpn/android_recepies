package com.example.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navDeepLink
import com.example.recipe.databinding.FragmentAddRecipeBinding

class AddRecipe : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {
        // Inflate the layout
        val binding: FragmentAddRecipeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_recipe, container, false)
        binding.cancelBtn.setOnClickListener { view: View -> }

            return binding.root
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}



