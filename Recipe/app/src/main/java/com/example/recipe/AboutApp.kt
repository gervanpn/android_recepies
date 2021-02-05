package com.example.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.recipe.databinding.FragmentAboutBinding

class AboutApp : Fragment() {

    private lateinit var binding : FragmentAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_about,
            container, false
        )

        binding.navigateButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_aboutFragment_to_home)
        }
        return binding.root
    }
}

