package com.leah.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leah.myapplication.databinding.FragmentHomeBinding
import com.leah.myapplication.repository.dao.RecipeDao
import com.leah.myapplication.repository.db.RecipeDatabase
import com.leah.myapplication.repository.entity.IngredientEntity
import com.leah.myapplication.repository.entity.PreparationStepEntity
import com.leah.myapplication.repository.entity.RecipeEntity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        top text - Our Recipes
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        // navigation


        /*
        * Add new recipe FAB
        *  -> launch recipe form
        *  -> save or load to sqlite DB
        *  -> refresh on the home page of the list
        * */
        /*
        * DB instance
        * */
        val database = activity?.let { RecipeDatabase.getDatabase(it.applicationContext) }
        val recipeDao = database?.recipeDao()

        return root
    }

    suspend fun addRecipe(recipeDao:RecipeDao){

        // dummy data
        val newRecipe = RecipeEntity(
            name = "Spaghetti Bolognese",
            mealType = "Dinner",
            serves = 4,
            difficulty = "Intermediate"
        )

        recipeDao.insertRecipe(newRecipe)


// Assuming you have a list of ingredients and preparation steps for the new recipe
        val ingredientsList: List<IngredientEntity> = listOf()// ...
        val preparationStepsList: List<PreparationStepEntity> = // ...

// Insert ingredients for the new recipe
            ingredientsList.forEach { ingredient ->
                ingredientDao.insertIngredient(ingredient.copy(recipeId = newRecipe.recipeId))
            }

// Insert preparation steps for the new recipe
        preparationStepsList.forEach { step ->
            preparationStepDao.insertPreparationStep(step.copy(recipeId = newRecipe.recipeId))
        }

    }


    suspend fun getRecipes(database:RecipeDatabase): List<RecipeEntity> {
        val recipes: List<RecipeEntity> = database.recipeDao().getAllRecipes();
        return recipes
    }

    suspend fun getIngredients(database:RecipeDatabase, recipId:Long): List<IngredientEntity> {
        val ingredients: List<IngredientEntity> = database.ingredientDao().getIngredientsForRecipe(recipId);
        return ingredients
    }

    /*suspend fun get(database:RecipeDatabase, recipId:Long): List<IngredientEntity> {
        val ingredients: List<IngredientEntity> = database.ingredientDao().getIngredientsForRecipe(recipId);
        return ingredients
    } */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}