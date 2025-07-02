package com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.data

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.CategorizedRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategorizedRecipeApi {

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Response<CategorizedRecipeResponse>

}