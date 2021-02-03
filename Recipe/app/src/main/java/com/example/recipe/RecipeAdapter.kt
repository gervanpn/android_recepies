package com.example.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import kotlin.collections.List

class RecipeAdapter(): RecyclerView.Adapter<RecipeViewHolder>() {
    val recipeList = ArrayList<Recipe>()
        lateinit var recipeViewModel :RecipeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
                DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        binding.myViewModel =   recipeViewModel
        return RecipeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe){
            recipe.recipeTitel = binding.rTitleView.toString()
        }
}