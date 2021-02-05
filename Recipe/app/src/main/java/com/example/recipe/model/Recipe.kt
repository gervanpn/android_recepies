package com.example.recipe.model

import android.os.Bundle

class Recipe constructor() {
    
    var recipe_instructions: String? = ""
    
    var recipe_category: String? = ""
    
    var recipe_detail: String? = ""
       
    var recipe_id: String? = ""
        get() = this.recipe_id

    var recipe_ingredients: String? = ""

    var recipe_name: String? = ""

    var recipe_picture: String? = ""

    var user_id: String? = ""

    var recipe_status: Boolean = false

    companion object {
        var recipeSelected = Bundle()
    }

}