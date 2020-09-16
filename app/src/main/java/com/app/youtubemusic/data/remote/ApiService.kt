package com.app.youtubemusic.data.remote

import com.app.youtubemusic.model.AuthToken
import com.app.youtubemusic.model.PlaylistResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    companion object {
        const val API_URL = "https://www.googleapis.com/"
    }

    @GET("youtube/v3/search")
    suspend fun fetchSearchResults(
        @Query("pageToken") nextPageToken: String,
        @Query("maxResults") maxResults: String,//20
        @Query("part") part: String,//snippet
        @Query("channelId") channelId: String,//UCEVHMYMwCYKAPbQQ_q7fHxw
        @Query("q") q: String,//bollywood
        @Query("type") type: String,//playlist
        @Query("key") key: String//AIzaSyA3jm4hz_yRefU9GAeDShFeKUyJFXcyzLw
    ): Response<PlaylistResponse>

    @GET("youtube/v3/playlists")
    suspend fun fetchPlaylists(
        @Header("Authorization") authorization:String,
        @Query("pageToken") nextPageToken: String,
        @Query("maxResults") maxResults: String,//20
        @Query("part") part1: String,//snippet,ContentDetails
        @Query("part") part2: String,
        @Query("mine") mine: String,//UCEVHMYMwCYKAPbQQ_q7fHxw
        @Query("key") key: String//AIzaSyA3jm4hz_yRefU9GAeDShFeKUyJFXcyzLw
    ): Response<PlaylistResponse>

    @FormUrlEncoded
    @POST("oauth2/v4/token")
    fun  fetchAuthToken(
        @Field("code") authcode: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("redirect_uri") redirect_uri: String,
        @Field("grant_type") grant_type: String,
    ): Call<AuthToken>

    @FormUrlEncoded
    @POST("oauth2/v4/token")
    fun  refreshAuthToken(
        @Field("refresh_token") authToken: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String,
    ): Call<AuthToken>

}