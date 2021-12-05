package com.example.itunesapi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongInfo (
    val artistName : String?,
    val trackName : String?,
    @Json( name = "previewUrl") val demoLink : String?,
    val trackPrice : Double?,
    val releaseDate : String?,
    val country : String,
    @Json( name = "artworkUrl100") val photo  : String?,
    val trackId : Double?
    ) : Parcelable