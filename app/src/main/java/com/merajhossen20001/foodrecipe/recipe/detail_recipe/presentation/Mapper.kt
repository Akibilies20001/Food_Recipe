package com.merajhossen20001.foodrecipe.recipe.detail_recipe.presentation

import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.domain.Meal
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.domain.MealDetail


fun MealDetail.getIngredientMeasurePairs(): List<Pair<String, String>> {
    return (1..20).mapNotNull { i ->
        try {
            val ingredientField = MealDetail::class.java.getDeclaredField("strIngredient$i")
            val measureField = MealDetail::class.java.getDeclaredField("strMeasure$i")

            ingredientField.isAccessible = true
            measureField.isAccessible = true

            val ingredient = ingredientField.get(this) as? String
            val measure = measureField.get(this) as? String

            if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
                ingredient to measure
            } else null
        } catch (e: Exception) {
            null // skip if field not found or something goes wrong
        }
    }
}

//fun MealDetail.getIngredientMeasurePairs(): List<Pair<String, String>> {
//    return (1..20).mapNotNull { index ->
//        val ingredient = Meal::class.java.getDeclaredField("strIngredient$index").apply { isAccessible = true }
//            .get(this) as? String
//        val measure = Meal::class.java.getDeclaredField("strMeasure$index").apply { isAccessible = true }
//            .get(this) as? String
//
//        if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
//            ingredient to measure
//        } else null
//    }
//}
