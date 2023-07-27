package com.leah.myapplication.repository.model

import android.content.ContentValues
import android.content.Context
import com.leah.myapplication.repository.RecipeContract
import com.leah.myapplication.repository.RecipeDbHelper

class RecipeDAO (context: Context) {
        private val dbHelper = RecipeDbHelper(context)

        fun addRecipe(recipe: Recipe) {
            val db = dbHelper.writableDatabase

            // Step 1: Insert the recipe details into the 'recipe_details' table
            val recipeValues = ContentValues().apply {
                put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.name)
                put(RecipeContract.RecipeEntry.COLUMN_MEAL_TYPE, recipe.mealType)
                put(RecipeContract.RecipeEntry.COLUMN_SERVES, recipe.serves)
                put(RecipeContract.RecipeEntry.COLUMN_DIFFICULTY, recipe.difficulty)
            }

            val recipeId = db.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, recipeValues)

            // Step 2: Insert the ingredients into the 'ingredients' table
            recipe.ingredients.forEach { ingredient ->
                val ingredientValues = ContentValues().apply {
                    put(RecipeContract.IngredientsEntry.COLUMN_RECIPE_ID, recipeId)
                    put(RecipeContract.IngredientsEntry.COLUMN_INGREDIENT_NAME, ingredient.name)
                    put(RecipeContract.IngredientsEntry.COLUMN_AMOUNT, ingredient.amount)
                }

                db.insert(RecipeContract.IngredientsEntry.TABLE_NAME, null, ingredientValues)
            }

            // Step 3: Insert the preparation steps into the 'preparation_steps' table
            recipe.preparationSteps.forEach { step ->
                val preparationStepValues = ContentValues().apply {
                    put(RecipeContract.PreparationStepsEntry.COLUMN_RECIPE_ID, recipeId)
                    put(RecipeContract.PreparationStepsEntry.COLUMN_STEP_NUMBER, step.stepNumber)
                    put(RecipeContract.PreparationStepsEntry.COLUMN_DESCRIPTION, step.description)
                }

                db.insert(RecipeContract.PreparationStepsEntry.TABLE_NAME, null, preparationStepValues)
            }

            db.close()
        }
    }
