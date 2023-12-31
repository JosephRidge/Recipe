package com.leah.myapplication.repository.dao


import com.leah.myapplication.repository.entity.PreparationStepEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PreparationStepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreparationStep(step: PreparationStepEntity)

    @Query("SELECT * FROM preparation_steps WHERE recipeId = :recipeId")
    suspend fun getPreparationStepsForRecipe(recipeId: Long): List<PreparationStepEntity>

    // Add more query methods as needed
}
