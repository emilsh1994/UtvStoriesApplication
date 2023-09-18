package ru.avantys.utv.domain.model

data class Story(
    val name: String,
    val url: String,
    val logoUrl: String,
    val isFavourite: Boolean = false
)
