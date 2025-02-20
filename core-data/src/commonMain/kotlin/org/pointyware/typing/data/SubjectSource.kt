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

val vocabUri = FileUri(0, "files/vocab.json")
val storiesUri = FileUri(1, "files/stories.json")
object SubjectSourceRegistry {

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()
    init {
        put(vocabUri)
        put(storiesUri)
    }

    fun put(subjectSource: SubjectSource) {
        map[subjectSource.id] = subjectSource
    }

    fun get(id: Int): SubjectSource? {
        return map[id]
    }
}
