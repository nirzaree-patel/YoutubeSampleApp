package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class Localized(
    @SerializedName("description")
    var descriptionLocalized: String="",
    @SerializedName("title")
    var titleLocalized: String=""
) : Parcelable