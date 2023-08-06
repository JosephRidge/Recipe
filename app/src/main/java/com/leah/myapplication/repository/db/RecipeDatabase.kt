package com.leah.myapplication.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leah.myapplication.repository.dao.IngredientDao
import com.leah.myapplication.repository.dao.PreparationStepDao
import com.leah.myapplication.repository.dao.RecipeDao
import com.leah.myapplication.repository.entity.IngredientEntity
import com.leah.myapplication.repository.entity.PreparationStepEntity
import com.leah.myapplication.repository.entity.RecipeEntity
import com.leah.myapplication.ui.home.HomeFragment
import kotlin.reflect.KClass

@Database(entities = [RecipeEntity::class, IngredientEntity::class, PreparationStepEntity::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
//    abstract fun ingredientDao(): IngredientDao
//    abstract fun preparationStepDao(): PreparationStepDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null


        // singleton approach - only once instances is declared throughout the application
        fun getDatabase(context: Context): RecipeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}