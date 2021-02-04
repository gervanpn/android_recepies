package com.example.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.example.recipe.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.List

class RecipeAdapter(val recipeList: ArrayList<Recipe> = ArrayList<Recipe>()): RecyclerView.Adapter<RecipeViewHolder>() {
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
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(recipe: Recipe){
        binding.rTitleView.text = recipe.recipe_name

    }
}
