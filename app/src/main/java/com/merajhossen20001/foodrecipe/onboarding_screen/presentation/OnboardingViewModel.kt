package com.merajhossen20001.foodrecipe.onboarding_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel@Inject constructor(
    private val appUseCases: AppUseCases
):ViewModel() {

    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.saveAppEntry ->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appUseCases.saveAppEntry()
        }

    }

}