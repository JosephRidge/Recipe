package com.leah.myapplication.repository.entity


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "preparation_steps",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class PreparationStepEntity(
    @PrimaryKey(autoGenerate = true)
    val stepId: Long = 0,
    val recipeId: Long,
    val stepNumber: Int,
    val description: String
)
