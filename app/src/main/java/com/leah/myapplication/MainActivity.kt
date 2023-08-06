package com.leah.myapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leah.myapplication.repository.RecipeDbHelper
import com.leah.myapplication.repository.db.RecipeDatabase
import com.leah.myapplication.repository.entity.RecipeEntity

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbHelper: RecipeDbHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = RecipeDbHelper(this) // sqlite db helper intiaLlize with the mainactivity context
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)//.navView

        /*
        * Create first instance of the Room DB
        * */
        val database = RecipeDatabase.getDatabase(this)
        val recipeDao = database.recipeDao()


        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_new_recipe
            )
        )
      /*  val addRecipe:FloatingActionButton = findViewById(R.id.addRecipe)
        addRecipe.setOnClickListener {
            // navigate to the next fragment
            navController.navigate(R.id.addNewRecipe)

        } */
        navView.setupWithNavController(navController)
    }
    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

}
