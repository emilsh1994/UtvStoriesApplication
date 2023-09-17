package ru.avantys.utv.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("detail")
    val result: StoriesDto
)