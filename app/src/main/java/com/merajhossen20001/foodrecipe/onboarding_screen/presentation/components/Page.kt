package com.merajhossen20001.foodrecipe.onboarding_screen.presentation.components

import androidx.annotation.DrawableRes
import com.merajhossen20001.foodrecipe.R

data class Page(
    val name: String,
    val description:String,
    @DrawableRes val image :Int
)


val pages = listOf(
    Page(
        name = "Pizza Margherita",
        description = "A classic Neapolitan pizza with tomato, mozzarella, and basil.",
        image = R.drawable.pizza_margherita
    ),
    Page(
        name = "Sushi",
        description = "A Japanese dish made with vinegared rice and raw fish or vegetables.",
        image = R.drawable.sushi
    ),
    Page(
        name = "Butter Chicken",
        description = "Creamy tomato-based curry with tender chicken, a favorite in Indian cuisine.",
        image = R.drawable.butter_chicken
    )
)