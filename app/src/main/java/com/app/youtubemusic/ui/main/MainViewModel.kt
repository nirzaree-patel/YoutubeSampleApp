package com.app.youtubemusic.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.youtubemusic.data.repository.PlaylistsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val playlistsRepository: PlaylistsRepository, application: Application) : AndroidViewModel(application) {

    val playlistList by lazy {
        playlistsRepository.observePagedPlaylists()
    }
}