package com.app.youtubemusic.factory

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.app.youtubemusic.data.local.dao.PlaylistDao
import com.app.youtubemusic.data.remote.ApiService
import com.app.youtubemusic.data.repository.PlaylistDataSource
import com.app.youtubemusic.model.Playlist
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PlaylistDataSourceFactory @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val scope: CoroutineScope,
    private val apiService: ApiService
) : DataSource.Factory<Int, Playlist>() {

    private val liveData = MutableLiveData<PlaylistDataSource>()

    override fun create(): DataSource<Int, Playlist> {
        val source = PlaylistDataSource(
            apiService,
            playlistDao,
            scope, context
        )
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 5
        lateinit var context: Context

        fun pagedListConfig(ctx: Context): PagedList.Config {
            context = ctx
            return PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()


        }
    }
}
