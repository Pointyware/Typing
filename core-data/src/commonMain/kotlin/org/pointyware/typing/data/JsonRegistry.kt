package org.pointyware.typing.data

import kotlinx.serialization.Serializable

/**
 * Describes the structure of a JSON registry file.
 */
@Serializable
data class JsonRegistry(
    val words: List<String>,
    val paragraphs: List<String>
)
