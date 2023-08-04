package com.leah.myapplication.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leah.myapplication.repository.entity.IngredientEntity

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity)

    @Query("SELECT * FROM ingredients WHERE recipeId = :recipeId")
    suspend fun getIngredientsForRecipe(recipeId: Long): List<IngredientEntity>

    // Add more query methods as needed
}
