package com.leah.myapplication.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


class RecipeDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            const val DATABASE_NAME = "recipe.db"
            const val DATABASE_VERSION = 1
        }

        override fun onCreate(db: SQLiteDatabase) {
            val createRecipeTable = "CREATE TABLE ${RecipeContract.RecipeEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${RecipeContract.RecipeEntry.COLUMN_NAME} TEXT NOT NULL, " +
                    "${RecipeContract.RecipeEntry.COLUMN_MEAL_TYPE} TEXT NOT NULL, " +
                    "${RecipeContract.RecipeEntry.COLUMN_SERVES} INTEGER NOT NULL, " +
                    "${RecipeContract.RecipeEntry.COLUMN_DIFFICULTY} TEXT NOT NULL)"

            val createIngredientsTable = "CREATE TABLE ${RecipeContract.IngredientsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${RecipeContract.IngredientsEntry.COLUMN_RECIPE_ID} INTEGER NOT NULL, " +
                    "${RecipeContract.IngredientsEntry.COLUMN_INGREDIENT_NAME} TEXT NOT NULL, " +
                    "${RecipeContract.IngredientsEntry.COLUMN_AMOUNT} TEXT NOT NULL)"

            val createPreparationStepsTable = "CREATE TABLE ${RecipeContract.PreparationStepsEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${RecipeContract.PreparationStepsEntry.COLUMN_RECIPE_ID} INTEGER NOT NULL, " +
                    "${RecipeContract.PreparationStepsEntry.COLUMN_STEP_NUMBER} INTEGER NOT NULL, " +
                    "${RecipeContract.PreparationStepsEntry.COLUMN_DESCRIPTION} TEXT NOT NULL)"

            db.execSQL(createRecipeTable)
            db.execSQL(createIngredientsTable)
            db.execSQL(createPreparationStepsTable)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // Implement database migration if needed
        }
    }
