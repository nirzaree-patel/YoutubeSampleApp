package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class Snippet(
    @SerializedName("channelId")
    var channelId: String="",
    @SerializedName("channelTitle")
    var channelTitle: String="",
    @SerializedName("description")
    var description: String="",
    @Embedded
    @SerializedName("localized")
    var localized: Localized= Localized(),
    @SerializedName("publishedAt")
    var publishedAt: String="",
    @Embedded
    @SerializedName("thumbnails")
    var thumbnails: Thumbnails=Thumbnails(),
    @SerializedName("title")
    var title: String=""
) : Parcelable