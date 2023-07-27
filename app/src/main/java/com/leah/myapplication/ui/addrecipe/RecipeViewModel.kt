package com.leah.myapplication.ui.addrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Our Recipes"
    }
    val text: LiveData<String> = _text

    private val _details = MutableLiveData<String>().apply {
        value = "Recipe Details"
    }
    val details: LiveData<String> = _details

    private val _ingredients = MutableLiveData<String>().apply {
        value = "Recipe Details"
    }
    val ingredients: LiveData<String> = _ingredients

    private val _preparation = MutableLiveData<String>().apply {
        value = "Recipe Preparation Steps"
    }
    val preparation: LiveData<String> = _preparation


}