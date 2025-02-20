package org.pointyware.typing.data

import kotlinx.serialization.Serializable

/**
 *
 */
@Serializable
sealed interface SubjectSource {

}

@Serializable
data class FileUri(
    val fileUriString: String
): SubjectSource
