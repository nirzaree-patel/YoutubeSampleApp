package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class PageInfo(
    @SerializedName("resultsPerPage")
    var resultsPerPage: Int,
    @SerializedName("totalResults")
    var totalResults: Int
) : Parcelable