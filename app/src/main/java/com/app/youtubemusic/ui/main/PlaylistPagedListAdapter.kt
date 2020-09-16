package com.app.youtubemusic.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.youtubemusic.R
import com.app.youtubemusic.databinding.RowPlaylistsBinding
import com.app.youtubemusic.model.Playlist
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PlaylistPagedListAdapter : PagedListAdapter<Playlist, PlaylistPagedListAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    inner class ViewHolder(val binding: RowPlaylistsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(Playlist: Playlist) {
            binding.tvPlaylistTitle.text = Playlist.snippet.title
            binding.tvPlaylistChannel.text = Playlist.snippet.channelTitle
            binding.tvPlaylistCount.text =
                "  \u25CF  " + Playlist.contentDetails.itemCount + " songs"
            binding.pgPlaylistImage.visibility = View.VISIBLE
            Picasso.get()
                .load(Playlist.snippet.thumbnails.medium.url)
                .error(R.drawable.youtube_music)
                .centerCrop()
                .resize(200, 200)
                .into(binding.ivPlaylistThumbnail, object : Callback {
                    override fun onSuccess() {
                        binding.pgPlaylistImage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.pgPlaylistImage.visibility = View.GONE
                    }
                })
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Playlist>() {
            override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist) =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RowPlaylistsBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}