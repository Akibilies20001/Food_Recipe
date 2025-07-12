package com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation


import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.Meal


data class SearchState(
    val searchQuery: String = "",
    val recipes: List<Meal> = emptyList()
)