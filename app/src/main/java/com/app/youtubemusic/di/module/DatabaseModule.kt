package com.app.youtubemusic.di.module

import android.app.Application
import com.app.youtubemusic.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(application: Application) = AppDatabase.invoke(application)

    @Singleton
    @Provides
    fun providesPlaylistDao(database: AppDatabase) = database.playlistDao()
}