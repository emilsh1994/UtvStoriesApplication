package ru.avantys.utv.data.dto

import com.google.gson.annotations.SerializedName

data class StoryDto(
    @SerializedName("news_name")
    val name: String = "",
    val url: String = "",
    val logoUrl: String = ""
)