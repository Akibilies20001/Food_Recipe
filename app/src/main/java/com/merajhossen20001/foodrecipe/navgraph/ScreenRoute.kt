package com.merajhossen20001.foodrecipe.navgraph

sealed class ScreenRoute(val route: String) {
    object OnboardingScreen : ScreenRoute("onboardingScreen")
    object OnboardingNavigation : ScreenRoute ("onboardingNavigation")

    object AppStartNavigation : ScreenRoute ("appStartNavigation")
    object FoodRecipeNavigator : ScreenRoute ("foodRecipeNavigator")

    object CategoryScreen : ScreenRoute ("categoryScreen")

    object CategorizedRecipeScreen : ScreenRoute ("categorizedRecipeScreen/{categoryName}"){
        fun createRoute(categoryName: String) = "categorizedRecipeScreen/$categoryName"
    }

    object SearchScreen : ScreenRoute ("searchScreen")
    object BookmarksScreen : ScreenRoute ("bookmarkScreen")
    object DetailScreen: ScreenRoute("detail_screen/{mealId}") {
        fun createRoute(mealId: String) = "detail_screen/$mealId"
    }


}