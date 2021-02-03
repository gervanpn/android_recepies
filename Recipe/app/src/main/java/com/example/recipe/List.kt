package com.example.recipe

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.databinding.FragmentListBinding
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth

class List : Fragment() {

    private lateinit var binding: FragmentListBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//    val user = firebaseAuth.currentUser!!.email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
//        var firebaseAuth = FirebaseAuth.getInstance()
//        checkSignInStatus()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = RecipeAdapter()

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

//    fun checkSignInStatus(){
//        if (firebaseAuth.currentUser != null) {
//            setHasOptionsMenu(true)
//            Log.d("Tag", "Signed in")
//        } else {
////            setHasOptionsMenu(false)
//            Log.d("Tag", "Not signed in")
//        }
//
//    }


}