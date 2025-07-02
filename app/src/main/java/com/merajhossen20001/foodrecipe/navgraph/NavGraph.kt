package com.merajhossen20001.foodrecipe.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.OnboardingScreen
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.OnboardingViewModel
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation.CategorizedRecipeScreen
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation.CategorizedRecipeViewModel
import com.merajhossen20001.foodrecipe.recipe.category.presentation.CategoryScreen
import com.merajhossen20001.foodrecipe.recipe.category.presentation.CategoryViewModel

@Composable
fun NavGraph(startDestination: String){

    val navController : NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination

    ) {
        navigation(
            route = ScreenRoute.OnboardingNavigation.route,
            startDestination = ScreenRoute.OnboardingScreen.route
        ){
            composable(
                route = ScreenRoute.OnboardingScreen.route
            ){
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(
                    event = {viewModel.onEvent(it)}
                )
            }
        }

        navigation(route = ScreenRoute.AppStartNavigation.route,
            startDestination = ScreenRoute.FoodRecipeNavigator.route){
            composable(route = ScreenRoute.FoodRecipeNavigator.route){
                val viewModel: CategorizedRecipeViewModel = hiltViewModel()
                CategorizedRecipeScreen(viewModel)
            }
        }
    }


}

