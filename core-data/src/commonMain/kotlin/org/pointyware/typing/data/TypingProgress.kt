package org.pointyware.typing.data

data class TypingProgress(
    val string: String,
    val incorrect: List<IntRange>
)
