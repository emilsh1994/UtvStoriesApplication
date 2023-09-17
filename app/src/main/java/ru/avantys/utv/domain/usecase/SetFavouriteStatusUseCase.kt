package ru.avantys.utv.domain.usecase

import javax.inject.Inject
import ru.avantys.utv.domain.model.Story
import ru.avantys.utv.domain.repository.StoriesRepository

class SetFavouriteStatusUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {

    suspend operator fun invoke(story: Story) {
        if (story.isFavourite) {
            storiesRepository.removeStoryFromFavourites(story.name)
        } else {
            storiesRepository.addFavouriteStory(story)
        }
    }
}
