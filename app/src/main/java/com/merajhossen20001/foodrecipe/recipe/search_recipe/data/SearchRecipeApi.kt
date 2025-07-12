package com.merajhossen20001.foodrecipe.recipe.search_recipe.data

import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRecipeApi {
    @GET("search.php")
    suspend fun searchRecipe(@Query("s") mealName: String): Response<SearchResponse>
}