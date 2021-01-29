package com.example.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
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
    private var thiscontext: Context? = null

    private lateinit var binding : FragmentLoginSignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private val RC_SIGN_IN: Int = 1
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }

       override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

	   binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_sign_up,
			   container, false )
           if (container != null) {
               thiscontext = container.context
           }
	   var etEmail = binding.Email.text
	   var etPassword = binding.password.text

        binding.SignUpBtn.setOnClickListener {
            if (etEmail.toString().isEmpty()){
                Log.d("TAG", "do_Login:enter valid username")
            }
            if(etPassword.toString().isEmpty()){
                Log.d("TAG", "do_Login:enter password ")
            }
            firebaseAuth.createUserWithEmailAndPassword(etEmail.toString(), etPassword.toString())
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        val request = NavDeepLinkRequest.Builder
                            .fromUri("android-app://androidx.navigation.app/list".toUri())
                            .build()
                        findNavController().navigate(request)
                        Log.d("TAG", "do_Login: you are signed up ")
                        val user: FirebaseUser? = firebaseAuth.currentUser
                        Log.d("TAG", "do_Login:$user")
                    } else {
                        Log.d("TAG", "do_Login:login failed")
                        Toast.makeText(thiscontext,"Email already Exists",Toast.LENGTH_LONG).show()
                    }
                }
        }

	   binding.LoginBtn.setOnClickListener {
           if (etEmail.toString().isEmpty()){
               Log.d("TAG", "do_Login:enter valid username")
           }
           if(etPassword.toString().isEmpty()){
               Log.d("TAG", "do_Login:enter password ")
           }
		   firebaseAuth.signInWithEmailAndPassword(etEmail.toString(),etPassword.toString())
				   .addOnCompleteListener{ task->
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
            binding.textView.text = user.displayName
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
        val user: FirebaseUser? = firebaseAuth.getCurrentUser()
        Log.d("FB", user.toString())
    }
    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
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
                //Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }
}