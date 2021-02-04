package com.example.recipe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.List

class RecipeViewModel:ViewModel() {

    var recipes = MutableLiveData<List<Recipe>>()

//init {
//       Log.w("TestInit", "Error getting documents.")
//}
fun readFireStorData() {

    val db = FirebaseFirestore.getInstance()
    db.collection("recipes")
        .get()
        .addOnCompleteListener { task ->
            val result: StringBuffer = StringBuffer()
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    result.append(document.data.getValue("recipe_name"))
                    val recipe = Recipe(result.toString())
                    val recipeList = arrayListOf<Recipe>(recipe)
                    recipes.value = recipeList
                   // var recipe :Recipe = Recipe("")

                  //  Log.w("go", recipe.recipeTitel)
                }
            } else {
                Log.w("Test", "Error getting documents.", task.exception)
            }
        }

}
}