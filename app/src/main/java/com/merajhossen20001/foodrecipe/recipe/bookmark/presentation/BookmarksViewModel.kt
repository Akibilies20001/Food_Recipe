package com.merajhossen20001.foodrecipe.recipe.bookmark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkEntity
import com.merajhossen20001.foodrecipe.recipe.core.domain.local.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
):ViewModel (){


     val bookmarkedRecipes: StateFlow<List<BookmarkEntity>> =
         bookmarkRepository.getBookmarks().stateIn(
             scope = viewModelScope,
             started = SharingStarted.WhileSubscribed(5000),
             initialValue = emptyList()
         )

    fun removeBookmark(bookmark : BookmarkEntity){
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(bookmark)
        }
    }


}