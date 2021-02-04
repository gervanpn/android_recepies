package com.example.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipe.databinding.FragmentAddRecipeBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class AddRecipe : Fragment() {
    private lateinit var binding: FragmentAddRecipeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_recipe, container, false)
        binding.addPictureBtn.setOnClickListener {
            val title = binding.rTitleText
            val instructions = binding.rInstructionsText
            val ingredients = binding.rIngredientsText
            savaFireStore(title, instructions, ingredients)
        }
        return binding.root

    }

        fun getdata(){

        }

    fun savaFireStore(
        title: EditText,
        ingredients: EditText,
        instructions: EditText
    ) {
        val db = FirebaseFirestore.getInstance()

        val recipez: MutableMap<String, Any> = HashMap()
              title
        val name: String = title.getText().toString()
        recipez["recipeName"] = name
        instructions
        val instr: String = instructions.getText().toString()
        recipez["recipeInstructions"] = instr
        ingredients
        val ingred: String = ingredients.getText().toString()
        recipez["recipeIngredients"] = ingred

        db.collection("recipez")
            .add(recipez)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "TAG",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }


    }


}