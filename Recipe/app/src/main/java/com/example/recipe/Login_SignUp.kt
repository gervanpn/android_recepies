package com.example.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_login_sign_up.*


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

    // TODO: Rename and change types of parameters
   // private var param1: String? = null
   // private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //arguments?.let {
         //   param1 = it.getString(ARG_PARAM1)
          //  param2 = it.getString(ARG_PARAM2)
        //}

        firebaseAuth = FirebaseAuth.getInstance()

    }

       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {

	   // Inflate the layout for this fragment
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

						   Log.d("TAG", "do_Login:you are loggedin ")
                          // Navigation.createNavigateOnClickListener(R.id.action_home2_to_list)

						   val user: FirebaseUser? = firebaseAuth.currentUser
						   Log.d("TAG", "do_Login:$user")
					   }else {
						   Log.d("TAG", "do_Login:login failed")
					   }
				   }
	   }
	   return binding.root
       //return inflater.inflate(R.layout.fragment_login_sign_up, container, false)     // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            textView.text = user?.displayName
        }
    }

    override fun onStart() {
        super.onStart()

		val currentUser = firebaseAuth.currentUser
		Log.d("TAG", "do_Login:$currentUser")

        configureGoogleSignIn()
        google_button.setOnClickListener {
            signIn()
        }
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, Login_SignUp::class.java)
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Login_SingUp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login_SignUp().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                Log.d("SUC", user?.displayName!!)
                //Navigation.createNavigateOnClickListener(R.id.action_home2_to_list)
//                parentFragmentManager.commit {
//                    replace<RecipeList>(R.layout.fragment_login_sign_up)
//                    setReorderingAllowed(true)
//                    addToBackStack("name") // name can be null
//                }
//                getFragmentManager()
//                    ?.beginTransaction()
//                    ?.replace(R.id.login_SignUp, RecipeList.newInstance("", ""))
//                    ?.commit();

            }

            }
        }
//        private fun setupUI() {
//        google_button?.setOnClickListener {
//           Log.d("SET", "setting up ui code")
//            signIn()
//        }
//      }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        val user: FirebaseUser? = firebaseAuth.getCurrentUser()
        Log.d("FB", user.toString())
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
                //Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

	fun do_Login(){

	}

}