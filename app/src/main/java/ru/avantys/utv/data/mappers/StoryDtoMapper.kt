package ru.avantys.utv.data.mappers

import javax.inject.Inject
import ru.avantys.utv.data.remote.dto.StoryDto
import ru.avantys.utv.domain.model.Story

class StoryDtoMapper @Inject constructor() {

    fun transform(story: List<StoryDto>): List<Story> {
        return story.map {
            Story(
                name = it.name,
                url = it.url,
                logoUrl = it.logoUrl,
                isFavourite = false
            )
        }
    }
}
