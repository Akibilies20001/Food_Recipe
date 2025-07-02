package com.merajhossen20001.foodrecipe.recipe.category.data

import com.merajhossen20001.foodrecipe.recipe.category.domain.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoryResponse>
}