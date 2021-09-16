package com.devbyteviewer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devbyteviewer.domain.Video

@Entity
data class DatabaseVideo constructor(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "updated")
    val updated: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String

)

// Conversion
// Database Object to Domain Object
// extension function which converts from database objects to domain objects:
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video (
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}
