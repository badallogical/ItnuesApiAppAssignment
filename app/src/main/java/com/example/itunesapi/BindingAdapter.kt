package com.example.itunesapi

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunesapi.network.SongInfo
import com.example.itunesapi.overview.SongAdapter

@BindingAdapter("loadFromUrl")
fun bindImg(imgView : ImageView, url : String? ){

    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with( imgView.context )
            .load(imgUri)
            .into( imgView )
    }

}

@BindingAdapter("loadSongList")
fun bindSongList( recyclerView: RecyclerView , songList : List<SongInfo>? ){
    val adapter = recyclerView.adapter as SongAdapter
    adapter.submitList(songList)
}