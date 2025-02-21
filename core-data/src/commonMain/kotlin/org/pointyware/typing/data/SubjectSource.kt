package org.pointyware.typing.data

import kotlinx.serialization.Serializable

/**
 *
 */
@Serializable
sealed interface SubjectSource {
    val id: Int
}

@Serializable
data class FileUri(
    override val id: Int,
    val fileUriString: String
): SubjectSource
