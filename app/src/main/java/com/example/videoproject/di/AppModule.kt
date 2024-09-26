package com.example.videoproject.di

import android.app.Application
import androidx.room.Room
import com.example.videoproject.data.local.dao.VideoDao
import com.example.videoproject.data.local.db.VideoDatabase
import com.example.videoproject.data.remote.api.VideoApi
import com.example.videoproject.data.repo.VideoRepositoryImpl
import com.example.videoproject.domain.repo.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideVideoDatabase(appContext: Application) = Room.databaseBuilder(
        appContext,
        VideoDatabase::class.java,
        "videoDB"
    ).build()


    @Provides
    fun provideVideoDao(database: VideoDatabase) = database.videoDao()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://base-url/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideVideoApi(retrofit: Retrofit): VideoApi =
        retrofit.create(VideoApi::class.java)


    @Singleton
    @Provides
    fun provideVideoRepository(
        videoApi: VideoApi,
        videoDao: VideoDao,
        @BackgroundCoroutineScope coroutineScope: CoroutineScope
    ): VideoRepository =
        VideoRepositoryImpl(videoApi, videoDao, coroutineScope)

    @BackgroundCoroutineScope
    @Provides
    fun provideBackgroundCoroutineScope(): CoroutineScope =
         CoroutineScope(SupervisorJob() + Dispatchers.IO)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BackgroundCoroutineScope