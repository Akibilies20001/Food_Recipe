package com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation

sealed class SearchEvent {
    data class  UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object  SearchRecipe: SearchEvent()
}