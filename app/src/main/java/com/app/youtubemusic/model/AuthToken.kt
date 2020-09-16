package com.app.youtubemusic.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class AuthToken(
    @SerializedName("access_token")
    var accessToken: String = "",
    @SerializedName("expires_in")
    var expiresIn: Int = 0,
    @SerializedName("refresh_token")
    var refreshToken: String = "",
    @SerializedName("scope")
    var scope: String = "",
    @SerializedName("token_type")
    var tokenType: String = "",
    @SerializedName("id_token")
    var idToken: String = ""
) : Parcelable