package org.pointyware.typing.data

import kotlinx.serialization.Serializable

@Serializable
data class JsonStory(
    val title: String,
    val paragraphs: List<String>
)
