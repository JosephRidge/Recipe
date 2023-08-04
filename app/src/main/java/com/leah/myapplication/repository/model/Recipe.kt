package com.leah.myapplication.repository.model



    data class Ingredient(
        val name: String,
        val amount: String
    )

    data class PreparationStep(
        val stepNumber: Int,
        val description: String
    )
