package com.merajhossen20001.foodrecipe.recipe.detail_recipe.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkEntity
import com.merajhossen20001.foodrecipe.recipe.core.domain.local.BookmarkRepository
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.domain.MealDetail

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val bookmarkRepository: BookmarkRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var recipe by mutableStateOf<MealDetail?>(null)
        private set

    var isError by mutableStateOf<Boolean>(false)
        private set

    var isBookmarked by mutableStateOf<Boolean>(false)
        private set

    init {
        val mealId: String = savedStateHandle["mealId"] ?: run {
            Log.d("DetailViewModel", "Meal ID is missing in nav args. Using default.")
            "52772"
        }
        viewModelScope.launch { isBookmarked = bookmarkRepository.isBookmarked(mealId) }

        mealId.let {


            viewModelScope.launch {

                when (val result = mealRepository.searchMealById(it.toString())) {
                    is Result.Success -> {
                        val mealList = result.data.mealDetails


                        if (!mealList.isNullOrEmpty()) {
                            recipe = mealList[0]
                            isError = false

                        }
                    }

                    is Result.Error -> {
                        isError = true
                    }
                }
            }
        }
    }

    fun addToBookmark(bookmarkEntity: BookmarkEntity){
        viewModelScope.launch {
            bookmarkRepository.addBookmark(bookmarkEntity)
        }
        isBookmarked = true

    }

    fun removeBookmark(bookmark : BookmarkEntity){
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(bookmark)
        }
        isBookmarked = false
    }

}
