package com.merajhossen20001.foodrecipe.recipe.detail_recipe.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.merajhossen20001.foodrecipe.R


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {

    val context = LocalContext.current
    val meal by remember { derivedStateOf { viewModel.recipe } }


    if (viewModel.isError){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_network_error), // 👈 your drawable
                contentDescription = "Error",
                tint = Color.Red,
                modifier = Modifier.size(64.dp) // adjust size
            )
        }
    }
    else{
        meal?.let{ meal ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    Image(
                        painter = rememberAsyncImagePainter(meal.strMealThumb),
                        contentDescription = meal.strMeal,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f)
                    )
                }

                item {
                    Text(
                        text = meal.strMeal,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Instructions", style = MaterialTheme.typography.titleMedium)

                        Row {
                            IconButton(onClick = {
                                if (viewModel.isBookmarked) {
                                    viewModel.removeBookmark(meal.toBookmarkEntity())

                                } else {
                                    viewModel.addToBookmark(meal.toBookmarkEntity())
                                }
                            }) {

                                if (viewModel.isBookmarked) {
                                    Icon(Icons.Default.Delete, contentDescription = "Watch on YouTube")

                                } else {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_bookmark_border_24),
                                        contentDescription = "Watch on YouTube"
                                    )
                                }

                            }


                            meal.strYoutube.takeIf { it.isNotBlank() }?.let { url ->
                                IconButton(onClick = {
                                    launchYoutubeIntent(context = context, url = url)
                                }) {
                                    Icon(
                                        Icons.Default.PlayArrow,
                                        contentDescription = "Watch on YouTube"
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = meal.strInstructions.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                item {
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                meal?.let {
                    itemsIndexed(it.getIngredientMeasurePairs()) { index, pair ->
                        val (ingredient, measure) = pair
                        Text(
                            text = "$index. $ingredient - $measure",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }

}


//meal.getIngredientMeasurePairs().forEach { (ingredient, measure) ->
//    Text(
//        text = "$ingredient - $measure",
//        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
//    )
//}

fun launchYoutubeIntent(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


