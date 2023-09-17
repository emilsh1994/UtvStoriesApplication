package ru.avantys.utv.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.avantys.utv.data.local.database.Database
import ru.avantys.utv.data.local.database.StoriesDao

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "StoryDB").build()
    }

    @Singleton
    @Provides
    fun provideStoryDAO(database: Database): StoriesDao {
        return database.getStoriesDao()
    }
}
