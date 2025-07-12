package com.merajhossen20001.foodrecipe.recipe.core.domain

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.CategorizedRecipeResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import com.merajhossen20001.foodrecipe.recipe.category.domain.CategoryResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.NetworkError
import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.SearchResponse


interface MealRepository {
    suspend fun getAllCategories(): Result<CategoryResponse, NetworkError>
    suspend fun getMealsByCategory(category: String): Result<CategorizedRecipeResponse, NetworkError>
    suspend fun searchRecipeByName(name: String): Result<SearchResponse, NetworkError>
//    suspend fun searchMealById(id: String): Result<MealDetailResponse, NetworkError>
}