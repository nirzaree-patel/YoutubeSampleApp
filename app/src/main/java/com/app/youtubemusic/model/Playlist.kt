package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Playlist(


    @SerializedName("etag")
    var etag: String = "",

    @PrimaryKey
    @SerializedName("id")
    var id: String,

//    @PrimaryKey
//    @Embedded
//    @SerializedName("id")
//    var id: IdInfo = IdInfo(),

    @SerializedName("kind")
    var kind: String = "",

    @Embedded
    @SerializedName("snippet")
    var snippet: Snippet = Snippet(),

    @Embedded
    @SerializedName("contentDetails")
    var contentDetails: ContentDetails = ContentDetails()

) : Parcelable
