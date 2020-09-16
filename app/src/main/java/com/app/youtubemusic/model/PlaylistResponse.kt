package com.app.youtubemusic.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class PlaylistResponse(
    @SerializedName("etag")
    var etag: String,
    @SerializedName("items")
    var items: List<Playlist>,
    @SerializedName("kind")
    var kind: String,
    @SerializedName("nextPageToken")
    var nextPageToken: String,
    @SerializedName("prevPageToken")
    var prevPageToken: String,
    @SerializedName("pageInfo")
    var pageInfo: PageInfo
) : Parcelable