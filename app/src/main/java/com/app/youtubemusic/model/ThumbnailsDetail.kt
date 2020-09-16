package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class ThumbnailsDetail(
    @SerializedName("height")
    var height: Int=0,
    @SerializedName("url")
    var url: String="",
    @SerializedName("width")
    var width: Int=0
) : Parcelable