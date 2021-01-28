package com.example.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.recipe.databinding.FragmentHomeBinding



class Home : Fragment() {

    //var google_buton : GoogleSignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        binding.btnEntree.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_home2_to_list)
        )
        // Inflate the layout for this fragment
        return binding.root
    }

}
