package com.example.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.recipe.List.Companion.newInstance
import com.example.recipe.R.id.home2
import com.example.recipe.R.id.login_SignUp
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
import androidx.fragment.app.FragmentManager as AndroidxFragmentAppFragmentManager
import com.example.recipe.List as RecipeList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login_SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login_SignUp : Fragment() {
    var thiscontext: Context? = null
    var contain : Int = 0
    //var view : View? = null
    //var google_button : SignInButton? = null
    private lateinit var firebaseAuth: FirebaseAuth
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //google_button.
        //configureGoogleSignIn()
        firebaseAuth = FirebaseAuth.getInstance()
        //setupUI()
        //google_button = findViewByID(R.id.google_button)

    //setup()
//
//        ) {
//            Log.d("SET", "setting up ui code")
//            signIn()
//        }
    }

       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
           if (container != null) {
               thiscontext = container.context
           }

           return inflater.inflate(R.layout.fragment_login_sign_up, container, false)     // Inflate the layout for this fragment
        //return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        Log.d("USE", user.toString())
        if (user != null) {
            textView.text = user?.displayName
        }

    }

    override fun onStart() {
        super.onStart()
        configureGoogleSignIn()
        google_button.setOnClickListener {
            signIn()
        }


        val user = FirebaseAuth.getInstance().currentUser
        Log.d("USE", user.toString())


        if (user != null) {

            Log.d("STA", "Starting " + user.displayName)
        // startActivity(Home.getLaunchIntent(this))
            //finish()
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

                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                Log.d("SUC", user?.displayName!!)
                //var fragmentManager =
                //val intent = RecipeList.newInstance("","").activity?.intent
                //if (intent != null) startActivity(intent)
//                parentFragmentManager.commit {
//                    replace<RecipeList>(R.layout.fragment_login_sign_up)
//                    setReorderingAllowed(true)
//                    addToBackStack("name") // name can be null
//                }
                getFragmentManager()
////                //supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.login_SignUp, RecipeList.newInstance("", ""))
                    ?.commit();
                //findNavController(view.).navigate(R.id.home2)
            }
             //   startActivity(Home.getLaunchIntent(this))
            //} //else {
                //Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
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
                Log.d("ERROR",e.toString())
                //Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

}

