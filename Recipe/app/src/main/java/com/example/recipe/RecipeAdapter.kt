
package com.example.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.example.recipe.model.Recipe

class RecipeAdapter: RecyclerView.Adapter<RecipeViewHolder>() {

    var recipes = ArrayList<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes.get(position))
        Log.d("size inside", "" + recipes.size)
    }

    override fun getItemCount(): Int {
        Log.d("size", "" + recipes.size)
        return recipes.size
    }
}

class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    val recipeTitle = binding.rTitleView
    val recipePicture = binding.rImageView

    fun bind(recipe: Recipe) {
        this.recipeTitle.setText(recipe.recipe_name)
        recipePicture.setImageResource(R.drawable.recipe_test_image)
    }

}