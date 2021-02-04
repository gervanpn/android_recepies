package com.example.recipe.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.firestore.FirebaseFirestore

class RecipesViewModel(app: Application): AndroidViewModel(app) {

    init {
        Log.d("ViewModel", "Started")
    }
    val mapper = ObjectMapper()
    val recipes = ArrayList<Recipe>()
    val recipesLiveData = MutableLiveData<ArrayList<Recipe>>()
    private val context = app
    
    init {
        Log.d("Recipes received2: " , "VM Started")
        readFireStorData()
        recipesLiveData.value = recipes
    }
    
    override fun onCleared() {
        super.onCleared()
        // Dispose All your Subscriptions to avoid memory leaks
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public fun readFireStorData() {
        Log.d("read: " , recipes.toString())
        val db = FirebaseFirestore.getInstance()
        db.collection("recipes")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        var recipe = Recipe()
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        recipe = mapper.convertValue(document.data, Recipe::class.java)
                        recipes.add(recipe)
                    }
                } else {
                    Log.w("Test", "Error getting documents.", task.exception)
                }
            }
    }
}