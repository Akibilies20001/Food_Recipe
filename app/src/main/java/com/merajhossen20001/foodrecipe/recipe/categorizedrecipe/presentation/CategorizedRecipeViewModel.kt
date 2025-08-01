package com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.Meal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import javax.inject.Inject

@HiltViewModel
class CategorizedRecipeViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Meals for the selected category
    var mealList by mutableStateOf<List<Meal>>(emptyList())
        private set

    // Error state
    var errorMessage by mutableStateOf<String?>(null)
        private set
    private val categoryName: String = savedStateHandle["categoryName"] ?: "Seafood"
    // Extract categoryName from navigation argument
    //private val categoryName: String = checkNotNull(savedStateHandle["categoryName"])

    init {
        viewModelScope.launch {
            when (val result = mealRepository.getMealsByCategory(categoryName)) {
                is Result.Success -> {
                    mealList = result.data.meals
                }
                is Result.Error -> {
                    errorMessage = "Failed to load meals: ${result.error.name}"
                }
            }
        }
    }
}