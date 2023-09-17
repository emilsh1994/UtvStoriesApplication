package ru.avantys.utv.data.repository

import android.util.Log
import retrofit2.HttpException
import ru.avantys.utv.data.mappers.StoryDtoMapper
import ru.avantys.utv.data.remote.StoriesApi
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.domain.repository.StoriesRepository
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val api: StoriesApi,
    private val mapper: StoryDtoMapper
) : StoriesRepository {

    override suspend fun getStories(): List<Story> {
        return try {
            val stories = api.getStories().result.stories
            mapper.transform(stories)
        } catch (exception: HttpException) {
            Log.e(
                "StoriesRepository",
                "Status code: ${exception.code()}, message: ${exception.message()}"
            )
            emptyList()
        }
    }
}