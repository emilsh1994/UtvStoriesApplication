package ru.avantys.utv.data.remote

import retrofit2.http.GET
import ru.avantys.utv.data.dto.ResponseDto

interface StoriesApi {

    @GET("api/v0/stories")
    suspend fun getStories(): ResponseDto

}