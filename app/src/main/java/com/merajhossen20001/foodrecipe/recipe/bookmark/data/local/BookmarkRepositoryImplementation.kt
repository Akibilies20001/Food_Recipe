package com.merajhossen20001.foodrecipe.recipe.bookmark.data.local

import com.merajhossen20001.foodrecipe.recipe.core.domain.local.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImplementation @Inject constructor(
    private val bookmarkDao: BookmarkDao
): BookmarkRepository {
    override suspend fun addBookmark(bookmark: BookmarkEntity) {
        bookmarkDao.upsertBookmark(bookmark)
    }

    override suspend fun removeBookmark(bookmark: BookmarkEntity) {
        bookmarkDao.deleteBookmark(bookmark)
    }

    override  fun getBookmarks(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getAllBookmarks()
    }

    override suspend fun isBookmarked(id: String): Boolean {
        return bookmarkDao.getBookmarkById(id) != null
    }
}