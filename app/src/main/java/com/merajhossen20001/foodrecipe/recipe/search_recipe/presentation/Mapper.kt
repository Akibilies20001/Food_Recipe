package com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation

import com.merajhossen20001.foodrecipe.recipe.core.domain.Recipe
import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.Meal

fun Meal.toRecipe(): Recipe {
    return Recipe(
        idMeal = idMeal.orEmpty(),
        strMeal = strMeal.orEmpty(),
        strMealThumb = strMealThumb.orEmpty()
    )
}