package com.example.recipe.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SelectedRecipeViewModel(app: Application): AndroidViewModel(app) {
    val recipe = MutableLiveData<Recipe>()
    private val context = app
    
    init {
        
    }
    
    fun getRecipe() {
        
    }
}