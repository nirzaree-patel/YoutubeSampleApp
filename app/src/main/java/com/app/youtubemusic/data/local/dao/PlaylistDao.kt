package com.app.youtubemusic.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.youtubemusic.model.Playlist


@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylists(Playlist: List<Playlist>)

    @Query("Select * from 'Playlist'")
    fun getPlaylists(): DataSource.Factory<Int, Playlist>

}