package ru.avantys.utv.data.mappers

import ru.avantys.utv.data.dto.StoryDto
import ru.avantys.utv.domain.model.Story
import javax.inject.Inject

class StoryDtoMapper @Inject constructor() {

    fun transform(story: List<StoryDto>): List<Story> {
        return story.map {
            Story(
                name = it.name,
                url = it.name,
                logoUrl = it.name,
                isFavourite = false,
            )
        }
    }
}