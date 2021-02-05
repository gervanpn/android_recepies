package com.example.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.recipe.databinding.FragmentAddRecipeBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class AddRecipe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddRecipeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_recipe, container, false)
        binding.cancelBtn.setOnClickListener { view: View ->
            NavHostFragment.findNavController(this).navigate(R.id.action_addRecipe_to_list)
        }

        binding.saveBtn.setOnClickListener { view: View ->
            val title = binding.rTitleText
            val description = binding.rDescriptionText
            val ingredients = binding.rIngredientsText
            val instructions = binding.rInstructionsText
            val photo = ""
            val group = "unclassified"
            val public = false
            val userName = binding.user
            saveDataFireStore(
                title,
                description,
                ingredients,
                instructions,
                userName
            )
            NavHostFragment.findNavController(this).navigate(R.id.action_addRecipe_to_list)
        }

        return binding.root
    }

    fun saveDataFireStore(
        title: EditText,
        description: EditText,
        ingredients: EditText,
        instructions: EditText,
        userName: TextView
    ) {
        val db = FirebaseFirestore.getInstance()

        val recipes: MutableMap<String, Any> = HashMap()

        val name: String = title.getText().toString()
        recipes["recipe_name"] = name

        val descrip: String = description.getText().toString()
        recipes["recipe_description"] = descrip

        val ingred: String = ingredients.getText().toString()
        recipes["recipe_ingredients"] = ingred

        val instr: String = instructions.getText().toString()
        recipes["recipe_instructions"] = instr

        val uName: String = userName.getText().toString()
        recipes["user_id"] = uName

        db.collection("recipes")
            .add(recipes)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "TAG",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}



