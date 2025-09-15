package com.merajhossen20001.foodrecipe.recipe.bookmark.presentation

import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkEntity
import com.merajhossen20001.foodrecipe.recipe.core.domain.Recipe

fun BookmarkEntity.toRecipe():Recipe{
    return Recipe(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb

    )
}