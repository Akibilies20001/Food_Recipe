package com.merajhossen20001.foodrecipe.recipe.detail_recipe.domain

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("meals")
    val mealDetails: List<MealDetail>?
)