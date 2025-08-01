package com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation

import com.merajhossen20001.foodrecipe.recipe.core.domain.Recipe
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.Meal

fun Meal.toRecipe(): Recipe {
    return Recipe(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}