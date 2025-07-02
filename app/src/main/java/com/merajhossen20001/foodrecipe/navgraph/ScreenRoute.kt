package com.merajhossen20001.foodrecipe.navgraph

sealed class ScreenRoute(val route: String) {
    object OnboardingScreen : ScreenRoute("onboardingScreen")
    object AppStartNavigation : ScreenRoute ("appStartNavigation")
    object OnboardingNavigation : ScreenRoute ("onboardingNavigation")
    object FoodRecipeNavigator : ScreenRoute ("foodRecipeNavigator")

}