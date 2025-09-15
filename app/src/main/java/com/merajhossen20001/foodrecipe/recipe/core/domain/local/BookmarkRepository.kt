package com.merajhossen20001.foodrecipe.recipe.core.domain.local

import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun addBookmark(bookmark: BookmarkEntity)
    suspend fun removeBookmark(bookmark: BookmarkEntity)
     fun getBookmarks(): Flow<List<BookmarkEntity>>
    suspend fun isBookmarked(id: String): Boolean
}