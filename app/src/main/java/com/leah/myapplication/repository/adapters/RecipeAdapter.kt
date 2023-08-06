package com.leah.myapplication.repository.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.leah.myapplication.R
import com.leah.myapplication.repository.entity.RecipeEntity

class RecipeAdapter (context: Context, resource: Int, recipes: List<RecipeEntity>) :
    ArrayAdapter<RecipeEntity>(context, resource, recipes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.recipe_item, parent, false)

        val currentRecipe = getItem(position)

//        val recipeName = itemView.findViewById<TextView>(R.id.text_recipe_name)
//        val mealType = itemView.findViewById<TextView>(R.id.text_meal_type)
         val difficulty = itemView.findViewById<TextView>(R.id.text_difficulty)
          val recipeNameTextView: TextView = itemView.findViewById(R.id.recipe)
          val recipePrepTime: TextView = itemView.findViewById(R.id.timeTaken)
          val plateServings: TextView = itemView.findViewById(R.id.servings)
          val difficultyLevel: TextView = itemView.findViewById(R.id.difficultyLevel)
          val mealTime: TextView = itemView.findViewById(R.id.mealTime)
          val viewRecipeDetailsBtn : TextView = itemView.findViewById(R.id.readRecipe)

        recipeNameTextView.text =currentRecipe?.name
        recipePrepTime.text = currentRecipe?.timeTaken
        plateServings.text = currentRecipe?.serves.toString()
        difficultyLevel.text = currentRecipe?.difficulty
        mealTime.text = currentRecipe?.mealType

        viewRecipeDetailsBtn.setOnClickListener {
                val builder = AlertDialog.Builder(context)
            val dialogView = LayoutInflater.from(context).inflate(R.layout.recipe_details_dialog, null)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()
            val recipeName = dialogView.findViewById<TextView>(R.id.recipe)
            val recipePrepTime= dialogView.findViewById<TextView>(R.id.timeTaken)
            val plateServings = dialogView.findViewById<TextView>(R.id.servings)
            val mealType = dialogView.findViewById<TextView>(R.id.mealTime)
            val difficulty = dialogView.findViewById<TextView>(R.id.difficultyLevel)
            val ingredients = dialogView.findViewById<TextView>(R.id.ingredients)
            val prepationSteps = dialogView.findViewById<TextView>(R.id.prepSteps)
            val closeButton = dialogView.findViewById<TextView>(R.id.closeButton)

            recipeName.text = currentRecipe?.name
            recipePrepTime.text =currentRecipe?.timeTaken
            plateServings.text = currentRecipe?.serves.toString()
            difficulty.text = currentRecipe?.difficulty
            mealType.text = currentRecipe?.mealType
            ingredients.text = currentRecipe?.ingredients
            prepationSteps.text = currentRecipe?.prepration
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
        return itemView
    }
}