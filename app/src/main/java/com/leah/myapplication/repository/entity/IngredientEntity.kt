package com.leah.myapplication.repository.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class  IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Long = 0,
    val recipeId: Long,
    val name: String,
    val quantity: Double,
    val unit: String
)
