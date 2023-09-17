package ru.avantys.utv.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StoryDto(
    @SerializedName("news_name")
    val name: String = "",
    val url: String = "",
    @SerializedName("image_logo")
    val logoUrl: String = ""
)
