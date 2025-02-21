package org.pointyware.typing.data

/**
 * A registry of available subjects.
 */
object SubjectSourceRegistry { // TODO: load list of json files and register them on app startup

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()

    fun put(subjectSource: SubjectSource) {
        map[subjectSource.id] = subjectSource
    }

    fun get(id: Int): SubjectSource? {
        return map[id]
    }

    fun getAll(): List<SubjectSource> {
        return map.values.toList()
    }
}
