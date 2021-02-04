package com.example.recipe

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.example.recipe.model.Recipe
import com.example.recipe.util.DownloadImage
import kotlin.collections.List

class RecipeAdapter(val recipeList: ArrayList<Recipe> = ArrayList()): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    fun updateRecipe(newRecisList:List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecisList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var item: Recipe = recipeList[position]
        holder.bind(recipeList[position])
        holder?.binding.cardView.setOnClickListener {
            val bundle = bundleOf("name" to item.recipe_name, "picture" to item.recipe_picture, "instruction" to item.recipe_instructions )
            it.findNavController().navigate(R.id.action_list_to_detail_view, bundle)
            Log.d("clicked", "Clicked")
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

 inner class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
     fun bind(recipe: Recipe) {
         val imageView = binding.rImageView
         DownloadImage(imageView).execute(recipe.recipe_picture)
         binding.rTitleView.text = recipe.recipe_name
     }
 }
}
