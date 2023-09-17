package ru.avantys.utv.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StoriesDto(
    @SerializedName("stories")
    val stories: List<StoryDto>
)
