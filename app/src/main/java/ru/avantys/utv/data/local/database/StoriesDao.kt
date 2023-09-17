package ru.avantys.utv.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.avantys.utv.data.local.model.StoryModel

@Dao
interface StoriesDao {

    @Query("select * from stories")
    suspend fun getFavouriteStoriesList(): List<StoryModel>

    @Query("select * from stories where name = :storyTitle")
    suspend fun getFavouriteStory(storyTitle: String): StoryModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteStory(storyModel: StoryModel)

    @Query("delete from stories where name = :storyTitle")
    suspend fun deleteStoryFromFavourites(storyTitle: String)
}
