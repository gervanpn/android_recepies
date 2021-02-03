package com.example.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.List

class RecipeAdapter: RecyclerView.Adapter<RecipeViewHolder>() {
    //val recipeList: List<Recipe>
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
        holder.readFireStorData(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun readFireStorData(recipe: Recipe) {

        val db = FirebaseFirestore.getInstance()
        db.collection("recipes")
            .get()
            .addOnCompleteListener { task ->
                val result: StringBuffer = StringBuffer()
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        result.append(document.data.getValue("recipe_name"))
                        var recipe: Recipe = Recipe()
                        recipe.recipeTitel = result.toString()
                        recipe.recipeTitel = binding.rTitleView.toString()
                        Log.w("Init",  recipe.recipeTitel)
                    }
                } else {
                    Log.w("Test", "Error getting documents.", task.exception)
                }
            }
    }
}