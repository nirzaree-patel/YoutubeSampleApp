package com.app.youtubemusic.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.youtubemusic.data.local.dao.PlaylistDao
import com.app.youtubemusic.factory.PlaylistDataSourceFactory
import com.app.youtubemusic.model.Playlist
import com.app.youtubemusic.utils.NetworkUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaylistsRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
    val context: Context,
    private val playlistDataSourceFactory: PlaylistDataSourceFactory
) {

    fun observePagedPlaylists() =
        if (NetworkUtils.isOnline(context)) {
            getDataFromRemote()
        } else {
            getDataFromLocal()
        }




    private fun getDataFromRemote(): LiveData<PagedList<Playlist>> {
        return LivePagedListBuilder(
            playlistDataSourceFactory,
            PlaylistDataSourceFactory.pagedListConfig(context)
        ).build()
    }

    private fun getDataFromLocal(): LiveData<PagedList<Playlist>> {
        val dataSourceFactory = playlistDao.getPlaylists()
        return LivePagedListBuilder(
            dataSourceFactory,
            PlaylistDataSourceFactory.pagedListConfig(context)
        ).build()
    }

}