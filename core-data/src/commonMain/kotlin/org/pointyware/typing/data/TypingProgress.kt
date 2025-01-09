package org.pointyware.typing.data

data class TypingProgress(
    /**
     * The current input string.
     */
    val string: String,
    /**
     * The range of incorrect characters in the input when compared to the current typing subject.
     */
    val incorrect: List<IntRange>,
    /**
     * The average words per minute calculated using an average word length of 5 characters.
     */
    val wpm: Float,
    /**
     * The fraction of correct characters in the input when compared to the current typing subject.
     * In the range [0, 1].
     */
    val accuracy: Float,
)
