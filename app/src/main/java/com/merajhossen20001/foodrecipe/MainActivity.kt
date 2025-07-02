package com.merajhossen20001.foodrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.merajhossen20001.foodrecipe.navgraph.NavGraph
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.OnboardingScreen
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.OnboardingViewModel
import com.merajhossen20001.foodrecipe.ui.theme.FoodRecipeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(
                condition = {viewModel.splashScreenCondition}
            )
        }
        enableEdgeToEdge()

        setContent {

            FoodRecipeTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    NavGraph(viewModel.startDestination)

                }
            }
        }
    }
}
