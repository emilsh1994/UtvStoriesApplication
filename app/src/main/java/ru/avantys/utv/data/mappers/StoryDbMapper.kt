package ru.avantys.utv.data.mappers

import javax.inject.Inject
import ru.avantys.utv.data.local.model.StoryModel
import ru.avantys.utv.domain.model.Story

class StoryDbMapper @Inject constructor() {

    fun transform(story: Story): StoryModel {
        return StoryModel(
            storyName = story.name,
            storyUrl = story.url,
            storyLogoUrl = story.logoUrl
        )
    }

    fun transform(storyList: List<StoryModel>): List<Story> {
        return storyList.map { story ->
            Story(
                name = story.storyName,
                url = story.storyUrl,
                logoUrl = story.storyLogoUrl,
                isFavourite = true
            )
        }
    }
}
