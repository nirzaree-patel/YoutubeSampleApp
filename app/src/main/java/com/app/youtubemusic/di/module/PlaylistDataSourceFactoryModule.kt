package com.app.youtubemusic.di.module

import com.app.youtubemusic.data.local.dao.PlaylistDao
import com.app.youtubemusic.data.remote.ApiService
import com.app.youtubemusic.factory.PlaylistDataSourceFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class PlaylistDataSourceFactoryModule {

    @Provides
    fun providePlaylistDataSourceFactory(
        playlistDao: PlaylistDao,
        scope: CoroutineScope,
        apiService: ApiService
    ) = PlaylistDataSourceFactory(
        playlistDao,
        scope,
        apiService
    )
}