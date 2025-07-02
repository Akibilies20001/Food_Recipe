package com.merajhossen20001.foodrecipe.recipe.core.domain

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.CategorizedRecipeResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import com.merajhossen20001.foodrecipe.recipe.category.domain.CategoryResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.NetworkError


interface MealRepository {
    suspend fun getAllCategories(): Result<CategoryResponse, NetworkError>
    suspend fun getMealsByCategory(category: String): Result<CategorizedRecipeResponse, NetworkError>
//    suspend fun searchMealByName(name: String): Result<MealResponse, NetworkError>
//    suspend fun searchMealById(id: String): Result<MealDetailResponse, NetworkError>
}