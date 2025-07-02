package com.merajhossen20001.foodrecipe.recipe.core.data

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.data.CategorizedRecipeApi
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.CategorizedRecipeResponse
import com.merajhossen20001.foodrecipe.recipe.category.data.CategoryApi
import com.merajhossen20001.foodrecipe.recipe.category.domain.CategoryResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.NetworkError
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result

import javax.inject.Inject

class MealRepositoryImplementation @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categorizedRecipeApi: CategorizedRecipeApi,
    private val apiCall: SafeApiCall
):MealRepository {
    override suspend fun getAllCategories(): Result<CategoryResponse, NetworkError> {
        return apiCall.execute{ categoryApi.getAllCategories()}
    }

    override suspend fun getMealsByCategory(category: String): Result<CategorizedRecipeResponse, NetworkError> {
        return apiCall.execute { categorizedRecipeApi.getMealsByCategory(category)}
    }


}