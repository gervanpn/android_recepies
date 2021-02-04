package com.example.recipe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.List

class DetailViewModel: ViewModel() {
    var recipesDetail =  MutableLiveData<List<Recipe>>()
    fun readFireStorData() {

        val db = FirebaseFirestore.getInstance()
        db.collection("recipes")
            .get()
            .addOnCompleteListener { task ->
                val result: StringBuffer = StringBuffer()
                if (task.isSuccessful) {
                    //recipe_name
                    for (document in task.result!!) {
                        result.append(document.data.getValue("recipe_name"))
                        val recipe = Recipe(result.toString())
                        val recipeList = arrayListOf<Recipe>(recipe)
                        recipesDetail.value =  recipeList
                        // var recipe :Recipe = Recipe("")

                         Log.w("go", recipe.recipeTitel.toString())
                    }
                } else {
                    Log.w("Test", "Error getting documents.", task.exception)
                }
            }

    }
}