package com.merajhossen20001.foodrecipe.recipe.category.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.recipe.category.domain.Category
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import javax.inject.Inject


@HiltViewModel
class  CategoryViewModel @Inject constructor(val mealRepository: MealRepository): ViewModel(){
    // List of categories exposed to the UI
    var categoryList by mutableStateOf<List<Category>>(emptyList())
        private set

    // Optional: for error handling or UI state
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var error by mutableStateOf<Boolean>(false)
        private set

    init {
        viewModelScope.launch {
            when (val result = mealRepository.getAllCategories()) {
                is Result.Success -> {
                    categoryList = result.data.categories
                }
                is Result.Error -> {
                    error = true
                    errorMessage = "Failed to load categories: ${result.error.name}"
                }

            }
        }
    }

}