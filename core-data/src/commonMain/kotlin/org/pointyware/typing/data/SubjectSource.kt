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

object SubjectSourceRegistry { // TODO: load list of json files and register them on app startup

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()

    fun put(subjectSource: SubjectSource) {
        map[subjectSource.id] = subjectSource
    }

    fun get(id: Int): SubjectSource? {
        return map[id]
    }

    fun get(): List<SubjectSource> {
        return map.values.toList()
    }
}
