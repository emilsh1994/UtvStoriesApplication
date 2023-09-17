package ru.avantys.utv.data.repository

import android.util.Log
import retrofit2.HttpException
import ru.avantys.utv.data.local.database.StoriesDao
import ru.avantys.utv.data.mappers.StoryDtoMapper
import ru.avantys.utv.data.mappers.StoryDbMapper
import ru.avantys.utv.data.remote.StoriesApi
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.domain.repository.StoriesRepository
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val api: StoriesApi,
    private val dao: StoriesDao,
    private val mapper: StoryDtoMapper,
    private val databaseMapper: StoryDbMapper
) : StoriesRepository {

    override suspend fun getStories(): List<Story> {
        return try {
            val databaseStories = databaseMapper.transform(dao.getFavouriteStoriesList())
            val networkStories = mapper.transform(api.getStories().result.stories)

            val filtered = networkStories.filterNot { stories ->
                databaseStories.any{ favouriteStory -> favouriteStory.name == stories.name }
            }
            databaseStories + filtered
        } catch (exception: HttpException) {
            Log.e(
                "StoriesRepository",
                "Status code: ${exception.code()}, message: ${exception.message()}"
            )
            emptyList()
        }
    }

    override suspend fun addFavouriteStory(story: Story) {
        dao.addFavouriteStory(databaseMapper.transform(story))
    }

    override suspend fun removeStoryFromFavourites(name: String) {
        dao.deleteStoryFromFavourites(name)
    }
}