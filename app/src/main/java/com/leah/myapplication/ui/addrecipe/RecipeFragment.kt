package com.leah.myapplication.ui.addrecipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leah.myapplication.R

class RecipeFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeFragment()
    }

    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        val details: TextView = view.findViewById(R.id.details)
        val ingredients: TextView = view.findViewById(R.id.ingredients)
        val preparation: TextView = view.findViewById(R.id.preparations)

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
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}