package com.leah.myapplication.ui.addrecipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.leah.myapplication.R
import com.leah.myapplication.repository.dao.RecipeDao
import com.leah.myapplication.repository.db.RecipeDatabase
import com.leah.myapplication.repository.entity.RecipeEntity
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class RecipeFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeFragment()
    }

    private lateinit var recipeDao: RecipeDao
    private lateinit var viewModel: RecipeViewModel
    private var selectedDifficulty:String =""
    private lateinit var progress:ProgressBar
    private var servings by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        val details: TextView = view.findViewById(R.id.details)
        val ingredients: TextView = view.findViewById(R.id.ingredients)
        val preparation: TextView = view.findViewById(R.id.preparations)
        val saveToDB: Button = view.findViewById(R.id.button_save)
        progress = view.findViewById(R.id.loader)
        progress.visibility = View.GONE
        // inputs
        val recipeName: EditText = view.findViewById(R.id.edit_recipe_name)
        val mealType: EditText = view.findViewById(R.id.edit_meal_type)
        val mealServings: EditText = view.findViewById(R.id.edit_serves)
        val ingredientsNeeded: EditText = view.findViewById(R.id.edit_ingredient_name)
        val preparationSteps: EditText = view.findViewById(R.id.edit_ingredient_name)
        val preparationTime: EditText = view.findViewById(R.id.edit_prepration_time)
        val difficultyRadio: RadioGroup = view.findViewById(R.id.radio_group_difficulty)


        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        viewModel.details.observe(viewLifecycleOwner) {
            details.text = it
        }
        viewModel.ingredients.observe(viewLifecycleOwner) {
            ingredients.text = it
        }
        viewModel.preparation.observe(viewLifecycleOwner) {
            preparation.text = it
        }

        // listen for change
        difficultyRadio.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
                if (container != null) {
                    selectedDifficulty = radio.text as String
                }
            })
        // Get radio group selected status and text using button click event
        // save to roomDB
        saveToDB.setOnClickListener {

            val recipe = recipeName.text.toString()
            val type = mealType.text.toString()
            if (mealServings.text.toString().isNotEmpty()) {
                servings = mealServings.text.toString().toInt()
            } else {
                servings = 0
            }
            val ingredientsToUse = ingredientsNeeded.text.toString()
            val preparationStp = preparationSteps.text.toString()
            val timeTaken  = preparationTime.text.toString()

            progress.visibility = View.VISIBLE
            insertRecipe(
                name = recipe, mealType = type,prepTime=timeTaken, serves = servings, difficulty = selectedDifficulty,
                ingredients = ingredientsToUse, preparation = preparationStp,
                recipeName=recipeName, type = mealType,mealServings=mealServings,
                ingredientsNeeded=ingredientsNeeded, preparationSteps=preparationSteps, timeTaken=preparationTime
            )

            val database = Room.databaseBuilder(
                requireContext(),
                RecipeDatabase::class.java,
                "recipe_database"
            ).build()

            recipeDao = database.recipeDao()


        }
        return view;
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun insertRecipe( name: String,mealType: String,prepTime:String, serves: Int,
                              difficulty: String, ingredients: String, preparation: String,
                              recipeName:EditText,type:EditText, mealServings:EditText, ingredientsNeeded:EditText,
                              preparationSteps:EditText, timeTaken: EditText  ) {
        progress.visibility = View.VISIBLE
        val recipe = RecipeEntity(
            name = name,
            mealType = mealType,
            serves = serves,
            difficulty = difficulty,
            ingredients = ingredients,
            prepration = preparation,
            timeTaken = prepTime
        )

        // Run the insertion in a coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            try{
                recipeDao.insertRecipe(recipe)
                // success
                println("Success!")
                progress.visibility = View.GONE
                Toast.makeText(
                        context, " $name has been Recipe Added!",
                        Toast.LENGTH_SHORT
                    ).show()

                // clear inputs
                 recipeName.text.clear()
                 type.text.clear()
                 mealServings.text.clear()
                 ingredientsNeeded.text.clear()
                 preparationSteps.text.clear()
                timeTaken.text.clear()
            }catch (e:java.lang.Exception)
            {
                println("Failed!")
                // failed
                progress.visibility = View.GONE
            }

        }
    }
}