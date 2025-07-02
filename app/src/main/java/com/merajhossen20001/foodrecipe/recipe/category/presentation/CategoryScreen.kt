package com.merajhossen20001.foodrecipe.recipe.category.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.merajhossen20001.foodrecipe.recipe.category.domain.Category


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel
) {
    val gridState = rememberLazyGridState()

    // Track scroll direction based on item index
    var previousItemIndex by remember { mutableStateOf(0) }

    val scrollDirection by remember {
        derivedStateOf {
            val currentIndex = gridState.firstVisibleItemIndex
            val delta = currentIndex - previousItemIndex
            previousItemIndex = currentIndex
            delta
        }
    }

    // Toggle AppBar alpha based on scroll direction
    val targetAlpha = when {
        scrollDirection > 0 -> 0f // hide when scrolling down
        scrollDirection < 0 -> 1f // show when scrolling up
        else -> 1f
    }

    // Animate alpha
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(150),
        label = "AppBarAlpha"
    )

    // Animate height based on alpha
    val animatedHeight by animateDpAsState(
        targetValue = if (animatedAlpha > 0f) 70.dp else 0.dp,
        animationSpec = tween(200),
        label = "AppBarHeight"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar with animated alpha and height
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                .height(animatedHeight)
                .fillMaxWidth()
                .statusBarsPadding()
                .graphicsLayer {
                    this.alpha = animatedAlpha
                }
                ,
            contentAlignment = Alignment.TopStart
        ) {
            if (animatedAlpha > 0f) {
                Text(
                    text = "Meal Categories",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
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
            items(viewModel.categoryList) { category ->
                CategoryCard(
                    category = category,
                    onClick = {
                        println("Clicked: ${category.strCategory}")
                    }
                )
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}


@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() },

    ) {
        val scrollState = rememberScrollState()

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
                contentDescription = category.strCategory,
                modifier = Modifier.padding(vertical = 8.dp).fillMaxHeight(.5f))
            Column(Modifier.verticalScroll(scrollState).padding( horizontal = 8.dp)) {
                Text(
                    text = category.strCategory,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = category.strCategoryDescription,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}
