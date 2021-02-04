package com.example.recipe

import android.icu.text.ListFormatter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDeepLinkRequest.Builder.fromUri
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.List

class RecipeAdapter(val recipeList: ArrayList<Recipe>): RecyclerView.Adapter<RecipeViewHolder>() {
    //val recipeList: List<Recipe>
    //val recipeList = ArrayList<Recipe>()
      // lateinit var recipeViewModel :RecipeViewModel
    fun updateRecipe(newRecisList:List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecisList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
                DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        //binding.myViewModel =   recipeViewModel
        return RecipeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
        holder.binding.cardView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_list_to_detail_view)
        }
        //holder.readFireStorData(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
fun bind(recipe: Recipe){
    binding.rTitleView.text = recipe.recipeTitel
}

}

//    fun readFireStorData(recipe: Recipe) {
//
//        val db = FirebaseFirestore.getInstance()
//        db.collection("recipes")
//            .get()
//            .addOnCompleteListener { task ->
//                val result: StringBuffer = StringBuffer()
//                if (task.isSuccessful) {
//                    for (document in task.result!!) {
//                        result.append(document.data.getValue("recipe_name"))
//                        recipe.recipeTitel = result.toString()
//                        recipe.recipeTitel = binding.rTitleView.toString()
//                        Log.w("go",  result.toString())
//                    }
//                } else {
//                    Log.w("Test", "Error getting documents.", task.exception)
//                }
//            }
//    }
