package com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.merajhossen20001.foodrecipe.R
import com.merajhossen20001.foodrecipe.ui.theme.FoodRecipeTheme

@Composable
fun OnboardingPage(page : Int){
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(pages[page].image),
            contentDescription = pages[page].name,
            modifier = Modifier.fillMaxHeight(.6f).fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = pages[page].name,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = pages[page].description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp) ,
            color = colorResource( R.color.text_body)

        )


    }


}
@PreviewLightDark
@Composable
fun OnboardingPagePreview(){
    FoodRecipeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
            OnboardingPage(1)

        }

    }
}

//Text(
//text = pages[page].name,
//fontStyle = MaterialTheme.typography.displayMedium
//)