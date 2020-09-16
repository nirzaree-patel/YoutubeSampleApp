package com.app.youtubemusic.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.youtubemusic.data.local.dao.PlaylistDao
import com.app.youtubemusic.model.Playlist

@Database(entities = [Playlist::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    lateinit var INSTANCE: AppDatabase

    abstract fun playlistDao(): PlaylistDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "YoutubePlaylist.db")
            .allowMainThreadQueries()
            .build()

    }

}