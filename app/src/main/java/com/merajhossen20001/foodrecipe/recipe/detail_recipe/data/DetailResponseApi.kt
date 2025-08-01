package com.merajhossen20001.foodrecipe.recipe.detail_recipe.data

import com.merajhossen20001.foodrecipe.recipe.detail_recipe.domain.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailResponseApi {
    @GET("lookup.php")
    suspend fun getMealsByCategory(@Query("i") id: String): Response<DetailResponse>
}