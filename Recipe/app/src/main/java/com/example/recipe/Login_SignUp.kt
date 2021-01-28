package com.example.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.recipe.databinding.FragmentLoginSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login_SignUp : Fragment() {
  private lateinit var binding: FragmentLoginSignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        Log.d(TAG, "do_Login:$currentUser")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_sign_up,
                container, false )
        var et_email = binding.Email.text
        var et_password = binding.password.text
        binding.LoginBtn.setOnClickListener {
            if (et_email.toString().isEmpty()){
                Log.d(TAG, "do_Login:enter valid username")
            }
            if(et_password.toString().isEmpty()){
                Log.d(TAG, "do_Login:enter password ")
            }
            auth.signInWithEmailAndPassword(et_email.toString(),et_password.toString())
                    .addOnCompleteListener{ task->
                        Log.d(TAG, "do_Login:$et_email")
                        Log.d(TAG, "do_Login:$et_password")
                        if(task.isSuccessful){
                            Log.d(TAG, "do_Login:you are loggedin ")
                            val user: FirebaseUser? = auth.currentUser
                            Log.d(TAG, "do_Login:$user")
                        }else {
                            Log.d(TAG, "do_Login:login failed")
                        }
                    }
        }
        return binding.root
    }

    fun do_Login(){

    }

}