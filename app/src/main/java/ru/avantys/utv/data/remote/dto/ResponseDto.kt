package ru.avantys.utv.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("detail")
    val result: StoriesDto
)