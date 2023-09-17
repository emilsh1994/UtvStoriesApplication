package ru.avantys.utv.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.avantys.utv.data.local.model.StoryModel

@Database(entities = [StoryModel::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun getStoriesDao(): StoriesDao
}
