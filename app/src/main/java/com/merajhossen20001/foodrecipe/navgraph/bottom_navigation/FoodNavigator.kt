package com.merajhossen20001.foodrecipe.navgraph.bottom_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.merajhossen20001.foodrecipe.R
import com.merajhossen20001.foodrecipe.navgraph.ScreenRoute
import com.merajhossen20001.foodrecipe.navgraph.bottom_navigation.components.BottomNavigationItem
import com.merajhossen20001.foodrecipe.navgraph.bottom_navigation.components.FoodBottomNavigation
import com.merajhossen20001.foodrecipe.recipe.bookmark.presentation.BookmarksViewModel
import com.merajhossen20001.foodrecipe.recipe.bookmark.presentation.BookmarkScreen
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation.CategorizedRecipeScreen
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.presentation.CategorizedRecipeViewModel
import com.merajhossen20001.foodrecipe.recipe.category.domain.Category
import com.merajhossen20001.foodrecipe.recipe.category.presentation.CategoryScreen
import com.merajhossen20001.foodrecipe.recipe.category.presentation.CategoryViewModel
import com.merajhossen20001.foodrecipe.recipe.core.domain.Recipe
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.presentation.DetailScreen
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.presentation.DetailViewModel
import com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation.SearchScreen
import com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation.SearchViewModel


@Composable
fun FoodNavigator(){

    val bottomFoodNavigationItems = remember {
         listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, name = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, name = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_bookmark_border_24, name = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember (key1 = backStackState){
        when(backStackState?.destination?.route){
            ScreenRoute.CategoryScreen.route -> 0
            ScreenRoute.SearchScreen.route -> 1
            ScreenRoute.BookmarksScreen.route -> 2
            else -> 0
    }


    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == ScreenRoute.CategoryScreen.route ||
                backStackState?.destination?.route == ScreenRoute.SearchScreen.route ||
                backStackState?.destination?.route == ScreenRoute.BookmarksScreen.route||
                backStackState?.destination?.route == ScreenRoute.CategorizedRecipeScreen.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                FoodBottomNavigation(
                    items = bottomFoodNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = ScreenRoute.CategoryScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = ScreenRoute.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = ScreenRoute.BookmarksScreen.route
                            )
                        }

                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = ScreenRoute.CategoryScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)){
            composable(
                route = ScreenRoute.CategoryScreen.route){
                val viewModel: CategoryViewModel = hiltViewModel()

                CategoryScreen(
                    viewModel = viewModel
                    ,
                    navigateToCategorizedScreen = {recipe->
                        navigateToCategorizedScreen(
                            navController = navController,
                            recipe = recipe
                        )


                    }
                )
            }

            composable(route = ScreenRoute.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetail = {
                        navigateToDetail(navController = navController,
                            recipe = it)

                    })
            }
            composable(route = ScreenRoute.DetailScreen.route,
                arguments = listOf(navArgument("mealId") {
                    type = NavType.StringType
                })){
                val viewModel: DetailViewModel = hiltViewModel()
                //viewModel.sideEffect
                        DetailScreen(
                            viewModel = viewModel,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )


            }
            composable( route = ScreenRoute.CategorizedRecipeScreen.route,
                arguments = listOf(navArgument("categoryName") {
                    type = NavType.StringType
                })
            )
            {
                val viewModel: CategorizedRecipeViewModel = hiltViewModel()
                CategorizedRecipeScreen(
                    viewModel = viewModel,
                    navigateToDetail = { recipe ->
                        navigateToDetail(navController, recipe)
                    }
                )
            }

            composable(route = ScreenRoute.BookmarksScreen.route){
                val viewModel:BookmarksViewModel = hiltViewModel()

                BookmarkScreen(
                    viewModel,
                    navigateToDetail = {recipe->
                        navigateToDetail(navController, recipe = recipe) },
                )

//                val viewModel: BookmarkViewModel = hiltViewModel()
//                val state = viewModel.state.value
//                BookmarkScreen(
//                    state = state,
//                    navigateToDetail = {article ->
//                        navigateToDetail(
//                            navController = navController,
//                            article = article
//                        )
//
//                    }
//                )
            }




        }
    }


}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true

        }
    }
}

private fun navigateToDetail(navController: NavController,
                             recipe: Recipe){
    navController.currentBackStackEntry?.savedStateHandle?.set("recipeId", recipe.idMeal)
    navController.navigate(
        route =   ScreenRoute.DetailScreen.createRoute(recipe.idMeal)
    )
}
private fun navigateToCategorizedScreen(navController: NavController,
                             recipe: Category){
    navController.currentBackStackEntry?.savedStateHandle?.set("categoryName", recipe.strCategory)
    navController.navigate(
        route =  ScreenRoute.CategorizedRecipeScreen.createRoute(recipe.strCategory)
    )
}