package com.leah.myapplication.repository.model

 data class Recipe(
        val name: String,
        val mealType: String,
        val serves: Int,
        val difficulty: String,
        val ingredients: List<Ingredient>,
        val preparationSteps: List<PreparationStep>
    )

    data class Ingredient(
        val name: String,
        val amount: String
    )

    data class PreparationStep(
        val stepNumber: Int,
        val description: String
    )
