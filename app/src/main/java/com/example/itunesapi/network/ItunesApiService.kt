package com.example.itunesapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://itunes.apple.com/"

val moshi: Moshi = Moshi.Builder()
    .add( KotlinJsonAdapterFactory()  )
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory( MoshiConverterFactory.create( moshi ) )
    .build()

public interface ItunesApiService {

    @GET("search")
    suspend fun getSongsInfo( @Query("term") term : String ) : ApiResponse

}

object ItunesApi{

    val retrofitApi : ItunesApiService by lazy {
        retrofit.create( ItunesApiService::class.java )
    }

}

