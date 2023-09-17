package ru.avantys.utv.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.avantys.utv.data.repository.StoriesRepositoryImpl
import ru.avantys.utv.domain.repository.StoriesRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindingModule {

    @Binds
    fun bindStoryRepository(storiesRepositoryImpl: StoriesRepositoryImpl): StoriesRepository
}
