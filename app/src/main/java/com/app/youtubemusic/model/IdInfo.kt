package com.app.youtubemusic.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class IdInfo(
    @SerializedName("kind")
    var playlistKind: String="",
    @SerializedName("playlistId")
    var playlistId: String="",
) : Parcelable