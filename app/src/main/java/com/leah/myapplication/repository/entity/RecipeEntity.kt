package com.leah.myapplication.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Long = 0,
    val name: String,
    val mealType: String,
    val serves: Int,
    val difficulty: String
)