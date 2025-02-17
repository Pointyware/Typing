package org.pointyware.typing.data

/**
 *
 */
sealed interface SubjectSource {

}

data class FileUri(
    val fileUriString: String
): SubjectSource
