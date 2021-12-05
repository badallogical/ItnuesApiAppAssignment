package com.example.itunesapi.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapi.network.ItunesApi
import com.example.itunesapi.network.SongInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

enum class SongsApiStatus { Loading, Error, Done }

class OverviewViewModel : ViewModel() {

    private val _songsInfoList = MutableLiveData<List<SongInfo>>()
    val songsInfoList: LiveData<List<SongInfo>>
        get() = _songsInfoList

    private val _songApiStatus = MutableLiveData<SongsApiStatus>()
    val songApiStatus: LiveData<SongsApiStatus>
        get() = _songApiStatus


    init {
        getSongInfo("jack")
    }

    fun getSongInfo(term: String) {
        Timber.d("getSongs Called")
        viewModelScope.launch {
            try {
                _songApiStatus.value = SongsApiStatus.Loading
                val apiResponse = ItunesApi.retrofitApi.getSongsInfo(term)

                if (apiResponse.results.isEmpty()) {
                    // TODO:
                    _songApiStatus.value = SongsApiStatus.Done
                } else {
                    _songsInfoList.value = apiResponse.results
                    _songApiStatus.value = SongsApiStatus.Done
                    Timber.d(" respnse " + apiResponse.results.size.toString())
                }

            } catch (exception: Exception) {
                _songApiStatus.value = SongsApiStatus.Error
                Timber.d("Exception " + exception.message)
            }

        }
    }

}