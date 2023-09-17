package ru.avantys.utv.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class StoryModel(

    @PrimaryKey
    @ColumnInfo(name = "name")
    val storyName: String,

    @ColumnInfo(name = "url")
    val storyUrl: String,

    @ColumnInfo(name = "logo_url")
    val storyLogoUrl: String

)
