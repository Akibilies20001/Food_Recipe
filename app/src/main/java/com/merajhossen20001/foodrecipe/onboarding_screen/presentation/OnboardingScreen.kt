package com.merajhossen20001.foodrecipe.onboarding_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components.LeftButton
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components.OnboardingPage
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components.PageIndicator
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components.RightButton
import com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components.pages
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(
    event: (OnboardingEvent)->Unit
){
    Column (Modifier.fillMaxSize()){
        val pagerState = rememberPagerState (
            initialPage = 0,
            pageCount = { pages.size }
        )

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0-> listOf("","Next")
                    1-> listOf("Back","Next")
                    2-> listOf("Back","Get Started")
                    else-> listOf("","")
                }

            }
        }

        HorizontalPager(state = pagerState) {index->
            OnboardingPage(page = index)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            PageIndicator(
                modifier = Modifier.width(48.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                val scope = rememberCoroutineScope()

                if (buttonState.value[1].isNotEmpty()){
                    LeftButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage-1)
                            }
                        }
                    )

                    RightButton(
                        text = buttonState.value[1],
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage==2){
                                    event(OnboardingEvent.saveAppEntry)
                                }else{
                                    pagerState.animateScrollToPage(page = pagerState.currentPage+1)
                                }


                            }
                        }
                    )





                }



            }


        }
        Spacer(Modifier.weight(.5f))
    }
}
