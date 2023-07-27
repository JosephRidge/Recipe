package com.leah.myapplication.repository

import android.provider.BaseColumns

object RecipeContract {
        object RecipeEntry : BaseColumns {
            const val TABLE_NAME = "recipe_details"
            const val COLUMN_NAME = "name"
            const val COLUMN_MEAL_TYPE = "meal_type"
            const val COLUMN_SERVES = "serves"
            const val COLUMN_DIFFICULTY = "difficulty"
        }

        object IngredientsEntry : BaseColumns {
            const val TABLE_NAME = "ingredients"
            const val COLUMN_RECIPE_ID = "recipe_id"
            const val COLUMN_INGREDIENT_NAME = "ingredient_name"
            const val COLUMN_AMOUNT = "amount"
        }

        object PreparationStepsEntry : BaseColumns {
            const val TABLE_NAME = "preparation_steps"
            const val COLUMN_RECIPE_ID = "recipe_id"
            const val COLUMN_STEP_NUMBER = "step_number"
            const val COLUMN_DESCRIPTION = "description"
        }
    }
