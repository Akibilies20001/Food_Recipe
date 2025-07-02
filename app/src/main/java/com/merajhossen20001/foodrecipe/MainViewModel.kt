package com.merajhossen20001.foodrecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.navgraph.ScreenRoute
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appUseCases: AppUseCases
): ViewModel() {

    var splashScreenCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(ScreenRoute.OnboardingNavigation.route)
        private set

    init {
        appUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            startDestination = if(shouldStartFromHomeScreen){
                ScreenRoute.AppStartNavigation.route
            } else{
                ScreenRoute.OnboardingNavigation.route
            }
            delay(500)
            splashScreenCondition = false
        }.launchIn(viewModelScope)
    }



}