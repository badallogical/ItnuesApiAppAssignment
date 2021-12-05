package com.example.itunesapi.network

data class ApiResponse(
    val resultCount : Double,
    val results : List<SongInfo>
)