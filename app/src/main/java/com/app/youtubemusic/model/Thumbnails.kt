package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Embedded

@SuppressLint("ParcelCreator")
@Parcelize
data class Thumbnails(
    @Embedded(prefix = "default_")
    @SerializedName("default")
    var default: ThumbnailsDetail=ThumbnailsDetail(),
    @Embedded(prefix = "high_")
    @SerializedName("high")
    var high: ThumbnailsDetail=ThumbnailsDetail(),
    @Embedded(prefix = "maxres_")
    @SerializedName("maxres")
    var maxres: ThumbnailsDetail=ThumbnailsDetail(),
    @Embedded(prefix = "medium_")
    @SerializedName("medium")
    var medium: ThumbnailsDetail=ThumbnailsDetail(),
    @Embedded(prefix = "standard_")
    @SerializedName("standard")
    var standard: ThumbnailsDetail=ThumbnailsDetail()
) : Parcelable
