package com.example.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
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


        /*myToolbar.setNavigationOnClickListener { view ->
            // Navigate somewhere
        }*/

        return binding.root
        //inflater.inflate(R.layout.fragment_about, container, false)
    }


}

