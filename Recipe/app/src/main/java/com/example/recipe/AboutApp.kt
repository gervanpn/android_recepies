package com.example.recipe

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.recipe.databinding.FragmentAboutBinding
import com.example.recipe.databinding.FragmentLoginSignUpBinding

class AboutApp : Fragment() {

    private lateinit var binding : FragmentAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,
            container, false )

        binding.navigateButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_aboutFragment_to_home)
        }
        /*myToolbar.setNavigationOnClickListener { view ->
            // Navigate somewhere
        }*/

        return binding.root
        //inflater.inflate(R.layout.fragment_about, container, false)
    }

}

