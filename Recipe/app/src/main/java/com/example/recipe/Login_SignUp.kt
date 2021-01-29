package com.example.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.recipe.databinding.FragmentLoginSignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider



class Login_SignUp : Fragment() {
    var thiscontext: Context? = null
    var contain : Int = 0
    //var view : View? = null
    //var google_button : SignInButton? = null
	private lateinit var binding : FragmentLoginSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }

       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {

	   binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_sign_up,
			   container, false )
           if (container != null) {
               thiscontext = container.context
           }
	   var et_email = binding.Email.text
	   var et_password = binding.password.text
	   binding.LoginBtn.setOnClickListener {
		   if (et_email.toString().isEmpty()){
			   Log.d("TAG", "do_Login:enter valid username")
		   }
		   if(et_password.toString().isEmpty()){
			   Log.d("TAG", "do_Login:enter password ")
		   }
		   firebaseAuth.signInWithEmailAndPassword(et_email.toString(),et_password.toString())
				   .addOnCompleteListener{ task->
					   Log.d("TAG", "do_Login:$et_email")
					   Log.d("TAG", "do_Login:$et_password")
					   if(task.isSuccessful){
                           val request = NavDeepLinkRequest.Builder
                               .fromUri("android-app://androidx.navigation.app/list".toUri())
                               .build()
                           findNavController().navigate(request)
						   Log.d("TAG", "do_Login: you are logged in ")
						   val user: FirebaseUser? = firebaseAuth.currentUser
						   Log.d("TAG", "do_Login:$user")
					   }else {
						   Log.d("TAG", "do_Login:login failed")
                           Toast.makeText(thiscontext,"Please enter valid credential",Toast.LENGTH_LONG).show()
					   }
				   }
	   }
	   return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            binding.textView.text = user?.displayName
        }
    }

    override fun onStart() {
        super.onStart()

		val currentUser = firebaseAuth.currentUser
		Log.d("TAG", "do_Login:$currentUser")

        configureGoogleSignIn()
        binding.googleButton.setOnClickListener {
            signIn()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                Log.d("SUC", user?.displayName!!)
                val request = NavDeepLinkRequest.Builder
                        .fromUri("android-app://androidx.navigation.app/list".toUri())
                        .build()
                findNavController().navigate(request)
            }
            }
        }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(thiscontext!!, mGoogleSignInOptions)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {
                Log.d("ERROR", e.toString())
                Toast.makeText(thiscontext, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }
}