package ru.avantys.utv.domain.repository

import ru.avantys.utv.domain.model.Story

interface StoriesRepository {
    suspend fun getStories(): List<Story>
}