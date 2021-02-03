package com.example.recipe.model

class Recipe constructor() {
    
    var recipe_Instructions: String? = null
    
    var recipe_category: String? = null
    
    var recipe_detail: String? = null
       
    var recipe_id: String? = null
        get() = this.recipe_id

    var recipe_ingredients: String? = null

    var recipe_name: String? = null

    var recipe_picture: String? = null

    var user_id: String? = null

    var recipe_status: Boolean = false

}