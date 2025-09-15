package com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mealRepository: MealRepository
): ViewModel() {

    private val _state = mutableStateOf(SearchState())

    val state : State<SearchState> = _state

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery )
            }

            is SearchEvent.SearchRecipe -> {
                searchRecipe()
            }
        }
    }

    private fun searchRecipe(){
        viewModelScope.launch {
            when (val result =
                mealRepository.searchRecipeByName(state.value.searchQuery)
            ) {
                is Result.Success -> {
                    if(result.data.meals == null){
                        _state.value = state.value.copy(searchError = true, isError = false)

                    }else{
                        _state.value = state.value.copy(recipes = result.data.meals, isError = false)
                    }

                }

                is Result.Error -> {
                    _state.value = state.value.copy(isError = true)
                }
            }
        }
    }
}