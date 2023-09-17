package ru.avantys.utv.domain.model

class Story(
    val name: String,
    val url: String,
    val logoUrl: String,
    val isFavourite: Boolean = false
)