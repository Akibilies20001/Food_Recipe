package com.merajhossen20001.foodrecipe.recipe.bookmark.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)
