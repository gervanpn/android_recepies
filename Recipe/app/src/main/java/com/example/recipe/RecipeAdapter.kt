package com.example.recipe

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.databinding.ListItemBinding
import com.google.firebase.firestore.FirebaseFirestore


class RecipeAdapter(): RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return RecipeViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       holder.readFireStorData()
    }

    override fun getItemCount(): Int {
        return 100
    }
}
class RecipeViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun readFireStorData(){
            val db = FirebaseFirestore.getInstance()
            db.collection("recipes")
                .get()
                .addOnCompleteListener { task ->
                    val result: StringBuffer = StringBuffer()
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                          result.append(document.data.getValue("recipe_name")).append(" ")
                        }
                        binding.rTitleView.setText(result)
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                    }
                }
        }
}