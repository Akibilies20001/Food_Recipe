package com.merajhossen20001.foodrecipe.di

import android.app.Application
import com.merajhossen20001.foodrecipe.onboarding_screen.data.LocalUserManagerImplementation
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.LocalUserManager
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.AppUseCases
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.ReadAppEntry
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.SaveAppEntry
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.data.CategorizedRecipeApi
import com.merajhossen20001.foodrecipe.recipe.category.data.CategoryApi
import com.merajhossen20001.foodrecipe.recipe.core.data.MealRepositoryImplementation
import com.merajhossen20001.foodrecipe.recipe.core.data.SafeApiCall
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application :Application
    ):LocalUserManager = LocalUserManagerImplementation( application )


    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager)
    = AppUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi = retrofit.create(CategoryApi::class.java)


    @Provides
    @Singleton
    fun provideCategorizedRecipeApi(retrofit: Retrofit): CategorizedRecipeApi
    = retrofit.create(CategorizedRecipeApi::class.java)



    @Provides
    @Singleton
    fun provideMealRepository(
        categoryApi: CategoryApi,
        categorizedRecipeApi: CategorizedRecipeApi,
        apiCall: SafeApiCall) : MealRepository = MealRepositoryImplementation(
        categoryApi = categoryApi,
        categorizedRecipeApi = categorizedRecipeApi,

        apiCall = apiCall
    )

    @Provides
    @Singleton
    fun provideSafeApiCall(): SafeApiCall = SafeApiCall()

}