package com.app.youtubemusic.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.youtubemusic.R
import com.app.youtubemusic.databinding.ActivityMainBinding
import com.app.youtubemusic.ui.base.BaseActivity
import com.app.youtubemusic.ui.login.LoginActivity
import com.app.youtubemusic.utils.Pref
import com.app.youtubemusic.utils.viewModelOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var auth: FirebaseAuth

    private val playlistAdapter by lazy {
        PlaylistPagedListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        auth = Firebase.auth

        mViewBinding.apply {
            playlistRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = playlistAdapter
            }

            if (auth.currentUser != null) {
                Picasso.get()
                    .load(auth.currentUser!!.photoUrl)
                    .error(R.drawable.ic_face)
                    .placeholder(R.drawable.ic_face)
                    .centerCrop()
                    .resize(200, 200)
                    .into(ivProfileImage)
            }

            ivLogout.setOnClickListener(){

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Logout")
                builder.setMessage("Are you sure, you want to logout?")
                builder.setPositiveButton("YES"){dialog, which ->
                    dialog.dismiss()
                    auth.signOut()
                    Pref.setValue(this@MainActivity, "authCode", "")
                    Pref.setValue(this@MainActivity, "authToken", "")
                    val intent: Intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                builder.setNegativeButton("No"){dialog,which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

        initObservers()

    }

    override fun getViewModel(): MainViewModel = viewModelOf<MainViewModel>(mViewModelProvider)

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun initObservers() {
        mViewModel.playlistList.observe(this, Observer {
            mViewBinding.progressBar.visibility = GONE
            mViewBinding.playlistRecyclerView.visibility = VISIBLE
            val list = it
            playlistAdapter.submitList(list)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}