package com.merajhossen20001.foodrecipe.recipe.bookmark.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Upsert
    suspend fun upsertBookmark( bookmark: BookmarkEntity)
    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE idMeal = :id LIMIT 1")
    suspend fun getBookmarkById(id: String): BookmarkEntity?
}