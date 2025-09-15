package com.merajhossen20001.foodrecipe.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.merajhossen20001.foodrecipe.onboarding_screen.data.LocalUserManagerImplementation
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.LocalUserManager
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.AppUseCases
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.ReadAppEntry
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases.SaveAppEntry
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.AppDatabase
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkDao
import com.merajhossen20001.foodrecipe.recipe.bookmark.data.local.BookmarkRepositoryImplementation
import com.merajhossen20001.foodrecipe.recipe.core.domain.local.BookmarkRepository
import com.merajhossen20001.foodrecipe.recipe.categorizedrecipe.data.CategorizedRecipeApi
import com.merajhossen20001.foodrecipe.recipe.category.data.CategoryApi
import com.merajhossen20001.foodrecipe.recipe.core.data.MealRepositoryImplementation
import com.merajhossen20001.foodrecipe.recipe.core.data.SafeApiCall
import com.merajhossen20001.foodrecipe.recipe.core.domain.MealRepository
import com.merajhossen20001.foodrecipe.recipe.detail_recipe.data.DetailResponseApi
import com.merajhossen20001.foodrecipe.recipe.search_recipe.data.SearchRecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideSearchRecipeApi(retrofit: Retrofit): SearchRecipeApi
            = retrofit.create(SearchRecipeApi::class.java)

    @Provides
    @Singleton
    fun provideDetailResponseApi(retrofit: Retrofit): DetailResponseApi
            = retrofit.create(DetailResponseApi::class.java)



    @Provides
    @Singleton
    fun provideMealRepository(
        categoryApi: CategoryApi,
        categorizedRecipeApi: CategorizedRecipeApi,
        searchRecipeApi: SearchRecipeApi,
        detailResponseApi: DetailResponseApi,
        apiCall: SafeApiCall) : MealRepository = MealRepositoryImplementation(
        categoryApi = categoryApi,
        categorizedRecipeApi = categorizedRecipeApi,
        searchRecipeApi = searchRecipeApi,
        apiCall = apiCall,
        detailResponseApi = detailResponseApi
    )

    @Provides
    @Singleton
    fun provideSafeApiCall(): SafeApiCall = SafeApiCall()


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "food_db"
        )
            .fallbackToDestructiveMigration() // wipes db on version change
            .build()
    }

    @Provides
    fun provideBookmarkDao(database: AppDatabase): BookmarkDao = database.bookmarkDao()

    @Provides
    @Singleton
    fun provideBookmarkRepository(dao: BookmarkDao): BookmarkRepository {
        return BookmarkRepositoryImplementation(dao)
    }

}