package com.devbyteviewer.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.devbyteviewer.domain.Video
import com.devbyteviewer.network.Network
import com.devbyteviewer.network.asDomainModel
import kotlinx.coroutines.launch
import java.io.IOException

class DevByteViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * A playlist of videos that can be shown on the screen. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private val _playlist = MutableLiveData<List<Video>>()
    val playlist: LiveData<List<Video>>
        get() = _playlist

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromNetwork()
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
    private fun refreshDataFromNetwork() =
        viewModelScope.launch {
            try {
                val playlist = Network.devbytes.getPlaylist()
                _playlist.postValue(playlist.asDomainModel())
            } catch (networkError: IOException) {
                // Show an infinite loading spinner if the request fails
                // challenge exercise: show an error to the user if the network request fails
            }
    }
}
