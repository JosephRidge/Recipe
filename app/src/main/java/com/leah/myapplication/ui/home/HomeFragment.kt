package com.leah.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leah.myapplication.R
import com.leah.myapplication.databinding.FragmentHomeBinding
import com.leah.myapplication.repository.adapters.RecipeAdapter
import com.leah.myapplication.repository.dao.RecipeDao
import com.leah.myapplication.repository.db.RecipeDatabase
import com.leah.myapplication.repository.entity.IngredientEntity
import com.leah.myapplication.repository.entity.PreparationStepEntity
import com.leah.myapplication.repository.entity.RecipeEntity
import com.leah.myapplication.ui.addrecipe.RecipeFragment
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recipeDao: RecipeDao
    private lateinit var loader:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loader = root.findViewById(R.id.loader)
        loader.visibility = View.VISIBLE

        val database = Room.databaseBuilder(
            requireContext(),
            RecipeDatabase::class.java,
            "recipe_database"
        ).build()

        recipeDao = database.recipeDao()

//      top text - Our Recipes
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val listView: ListView = root.findViewById(R.id.recipes)


        viewLifecycleOwner.lifecycleScope.launch {
            val recipes=  recipeDao.getAllRecipes()
            println("===> $recipes")
//            adapter. = recipes
            if(recipes.isNotEmpty()){
                loader.visibility = View.GONE
                Toast.makeText(
                    context, " Loading Recipes",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                loader.visibility = View.GONE
                Toast.makeText(
                    context, "No Recipes available",
                    Toast.LENGTH_LONG
                ).show()
            }
            val adapter = RecipeAdapter(requireContext(),R.id.recipes,recipes) // Initialize with empty list, update later
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        // set adapter then load to listview
        return root
    }

    private fun getAllRecipes(listView: ListView){

        // Run the insertion in a coroutine
        viewLifecycleOwner.lifecycleScope.launch {
           val recipes=  recipeDao.getAllRecipes()
            println("===> $recipes")
//            adapter. = recipes
            val adapter = RecipeAdapter(requireContext(),R.id.recipes,recipes) // Initialize with empty list, update later
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}