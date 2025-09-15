package com.merajhossen20001.foodrecipe.recipe.bookmark.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkEntity
import com.merajhossen20001.foodrecipe.recipe.core.domain.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
 viewModel: BookmarksViewModel,
 navigateToDetail:(Recipe)-> Unit
){

    val bookmarks by viewModel.bookmarkedRecipes.collectAsState()


Scaffold(
    topBar = {
        TopAppBar(title = { Text("Bookmarked Recipes")},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    },
   // containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
    padding ->
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = padding.calculateTopPadding()),
        contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),//verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(bookmarks){ bookmarkEntity ->

            BookmarkItem(
                bookmark = bookmarkEntity,
                onclick = {navigateToDetail(bookmarkEntity.toRecipe())},
                onDelete = {viewModel.removeBookmark(bookmarkEntity)}
            )

        }



    }

}
}


@Composable
fun BookmarkItem(
    bookmark :BookmarkEntity,
    onclick: () -> Unit,
    onDelete: () -> Unit
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {

        Row (Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically){

            Row(modifier = Modifier.weight(1f).clickable { onclick() },
                verticalAlignment =Alignment.CenterVertically,) {
                AsyncImage(bookmark.strMealThumb,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = bookmark.strMeal,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                    )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = bookmark.strMeal,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }

        }

    }

}