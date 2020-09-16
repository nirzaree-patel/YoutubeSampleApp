package com.app.youtubemusic.data.repository

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.paging.PageKeyedDataSource
import com.app.youtubemusic.R
import com.app.youtubemusic.data.local.dao.PlaylistDao
import com.app.youtubemusic.data.remote.ApiService
import com.app.youtubemusic.model.AuthToken
import com.app.youtubemusic.model.Playlist
import com.app.youtubemusic.utils.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class PlaylistDataSource @Inject constructor(
    private val apiService: ApiService,
    private val playlistDao: PlaylistDao,
    private val scope: CoroutineScope,
    private val context: Context
) : PageKeyedDataSource<Int, Playlist>() {

    private val TAG = "PlaylistDataSource"
    private var nextPageToken = ""
    private lateinit var auth: FirebaseAuth

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Playlist>) {

        if (!Pref.getValue(context, "authToken", "")!!.equals("")) {
            //refresh token
            refreshAuthToken(params, callback)
        } else if (!Pref.getValue(context, "authCode", "")!!.equals("")) {
            //auth token
            fetchAuthToken(params, callback)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Playlist>) {
        val page = params.key
        fetchPlaylist(nextPageToken, 20) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Playlist>) {
//        val page = params.key
//        fetchData(page, params.requestedLoadSize) {
//            callback.onResult(it,page-1)
//        }
    }

    private fun fetchPlaylist(nextPageToken: String, perPage: Int, callback: (List<Playlist>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val authToken: String = "Bearer "+Pref.getValue(context, "authToken", "").toString()
            val api_key: String = context.resources.getString(R.string.api_key)
            val response = apiService.fetchPlaylists(authToken,nextPageToken,"10","snippet","ContentDetails","true",api_key)
            Log.e("datasourse", "response-" + response)
            val remoteData = response.body()?.items

            // Check for response validation
            if (response.isSuccessful && remoteData != null) {
                // Save posts into the persistence storage
                playlistDao.insertPlaylists(remoteData)
                if (!response.body()?.nextPageToken.toString().equals("")) {
                    this@PlaylistDataSource.nextPageToken =
                        response.body()?.nextPageToken.toString()
                    callback(remoteData)
                }
            } else {
                // Something went wrong!
                postError(response.errorBody().toString())
            }
        }
    }

    private fun fetchSearch(nextPageToken: String, perPage: Int, callback: (List<Playlist>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val api_key: String = context.resources.getString(R.string.api_key)
            val response = apiService.fetchSearchResults(nextPageToken,"20","snippet","UCEVHMYMwCYKAPbQQ_q7fHxw","bollywood","playlist",api_key)
            Log.e("datasourse", "response-" + response)
            val remoteData = response.body()?.items

            // Check for response validation
            if (response.isSuccessful && remoteData != null) {
                // Save posts into the persistence storage
                playlistDao.insertPlaylists(remoteData)
                if (!response.body()?.nextPageToken.toString().equals("")) {
                    this@PlaylistDataSource.nextPageToken =
                        response.body()?.nextPageToken.toString()
                    callback(remoteData)
                }
            } else {
                // Something went wrong!
                postError(response.errorBody().toString())
            }
        }
    }

    private fun fetchAuthToken(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Playlist>){
        val authCode: String = Pref.getValue(context, "authCode", "").toString()
        val clientId: String = context.resources.getString(R.string.client_id)
        val clientSecret: String = context.resources.getString(R.string.client_secret)

        apiService.fetchAuthToken(
            authCode,
            clientId,
            clientSecret,
            "http://localhost:8080",
            "authorization_code"
        ).enqueue(object : Callback<AuthToken?> {
            override fun onResponse(
                call: Call<AuthToken?>,
                response: Response<AuthToken?>
            ) {

                val remoteData = response.body()

                // Check for response validation
                if (response.isSuccessful && remoteData != null) {
                    Pref.setValue(context, "authToken", remoteData.accessToken)
                    fetchPlaylist(nextPageToken, 20) {
                        callback.onResult(it, null, 2)
                    }
                } else {
                    // Something went wrong!
                    postError(response.errorBody().toString())
//                    auth = Firebase.auth
//                    auth.signOut()
                }
            }

            override fun onFailure(call: Call<AuthToken?>, throwable: Throwable) {
                throwable.printStackTrace()
                Log.v(TAG, "error : " + throwable.message)
            }
        })
    }

    private fun refreshAuthToken(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Playlist>){
        val authToken: String = Pref.getValue(context, "authToken", "").toString()
        val clientId: String = context.resources.getString(R.string.client_id)
        val clientSecret: String = context.resources.getString(R.string.client_secret)

        apiService.refreshAuthToken(
            authToken,
            clientId,
            clientSecret,
            "refresh_token"
        ).enqueue(object : Callback<AuthToken?> {
            override fun onResponse(
                call: Call<AuthToken?>,
                response: Response<AuthToken?>
            ) {

                val remoteData = response.body()

                // Check for response validation
                if (response.isSuccessful && remoteData != null) {
                    Pref.setValue(context, "authToken", remoteData.accessToken)
                    fetchPlaylist(nextPageToken, 20) {
                        callback.onResult(it, null, 2)
                    }
                } else {
                    // Something went wrong!
                    Pref.setValue(context, "authToken", "")
                    fetchAuthToken(params, callback)
                    postError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<AuthToken?>, throwable: Throwable) {
                throwable.printStackTrace()
                Log.v(TAG, "error : " + throwable.message)
            }
        })


    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.toString())
    }

    private fun postError(message: String) {
        Log.e(TAG, "An error happened: $message")
    }



}