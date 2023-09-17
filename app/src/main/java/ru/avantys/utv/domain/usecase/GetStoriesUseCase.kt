package ru.avantys.utv.domain.usecase

import javax.inject.Inject
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.domain.repository.StoriesRepository

class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend fun invoke(): List<Story> {
        return storiesRepository.getStories()
    }
}
