package com.merajhossen20001.foodrecipe.recipe.core.data

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.data.CategorizedRecipeApi
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.CategorizedRecipeResponse
import com.merajhossen20001.foodrecipe.recipe.category.data.CategoryApi
import com.merajhossen20001.foodrecipe.recipe.category.domain.CategoryResponse
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.NetworkError
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.data.DetailResponseApi
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.domain.DetailResponse
import com.merajhossen20001.foodrecipe.recipe.search_recipe.data.SearchRecipeApi
import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.SearchResponse

import javax.inject.Inject

class MealRepositoryImplementation @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categorizedRecipeApi: CategorizedRecipeApi,
    private val apiCall: SafeApiCall,
    private val searchRecipeApi: SearchRecipeApi,
    private val detailResponseApi: DetailResponseApi
):MealRepository {
    override suspend fun getAllCategories(): Result<CategoryResponse, NetworkError> {
        return apiCall.execute{ categoryApi.getAllCategories()}
    }

    override suspend fun getMealsByCategory(category: String): Result<CategorizedRecipeResponse, NetworkError> {
        return apiCall.execute { categorizedRecipeApi.getMealsByCategory(category)}
    }

    override suspend fun searchRecipeByName(name: String): Result<SearchResponse, NetworkError> {
        return apiCall.execute { searchRecipeApi.searchRecipe(name) }
    }

    override suspend fun searchMealById(id: String): Result<DetailResponse, NetworkError> {
        return apiCall.execute { detailResponseApi.getMealsByCategory(id = id) }
    }


}