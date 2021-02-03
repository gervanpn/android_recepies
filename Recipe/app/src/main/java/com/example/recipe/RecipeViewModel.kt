package com.example.recipe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class RecipeViewModel:ViewModel() {
    val inputName = MutableLiveData<String>()
init {
       Log.w("TestInit", "Error getting documents.")
}

    fun readFireStorData() {

        val db = FirebaseFirestore.getInstance()
        db.collection("recipes")
            .get()
            .addOnCompleteListener { task ->
               val result: StringBuffer = StringBuffer()
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        result.append(document.data.getValue("recipe_name"))
                       var recipe: Recipe = Recipe()
                        recipe.recipeTitel = result.toString()
                       // inputName.value = recipe.recipeTitel
                        Log.w("Init",  recipe.recipeTitel)
                    }
                } else {
                    Log.w("Test", "Error getting documents.", task.exception)
                }
            }
    }

}