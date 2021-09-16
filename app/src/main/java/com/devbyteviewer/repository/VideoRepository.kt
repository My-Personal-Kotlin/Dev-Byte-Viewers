package com.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.devbyteviewer.database.VideosDatabase
import com.devbyteviewer.domain.Video
import com.devbyteviewer.network.Network
import com.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.devbyteviewer.database.asDomainModel

class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> =
        Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }
    // Call to the network
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}