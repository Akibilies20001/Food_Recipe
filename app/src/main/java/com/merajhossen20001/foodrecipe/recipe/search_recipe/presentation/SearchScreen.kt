package com.merajhossen20001.foodrecipe.recipe.search_recipe.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import coil.compose.rememberAsyncImagePainter
import com.merajhossen20001.foodrecipe.recipe.search_recipe.domain.Meal


@Composable
fun SearchScreen(
    //viewModel: SearchViewModel,
    state: SearchState,
    event: (SearchEvent)-> Unit,
    //navigateToDetail : (Article) -> Unit
){
    val gridState = rememberLazyGridState()
    Column(
        modifier = Modifier.statusBarsPadding()
            .padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp
            ).fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {event(SearchEvent.SearchRecipe)},

            )

        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.recipes) { category ->
                CategoryCard(
                    category = category,
                    onClick = {
                        println("Clicked: ${category.strMeal}")
                    }
                )
            }
        }
        Spacer(Modifier.height(12.dp))

    }

}



@Composable
fun CategoryCard(
    category: Meal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        ) {
            // Background image
            Image(
                painter = rememberAsyncImagePainter(category.strMealThumb),
                contentDescription = category.strMeal,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
            )

            Text(
                text = category.strMeal,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f))
                        )
                    )
                    .padding(8.dp)
            )

            // Overlay text at bottom

        }
    }
}
