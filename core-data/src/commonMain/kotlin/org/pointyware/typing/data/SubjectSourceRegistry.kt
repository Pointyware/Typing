package org.pointyware.typing.data

import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

/**
 * A registry of available subjects.
 */
object SubjectSourceRegistry { // TODO: load list of json files and register them on app startup

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()

    private val json = getKoin().get<Json>()

    suspend fun loadFrom(
        fileBytes: ByteArray,
        wordMapper: ResourceUriMapper,
        paragraphMapper: ResourceUriMapper
    ) {
        // decode ByteArray to String
        val builder = StringBuilder()
        fileBytes.forEach { builder.append(it.toInt().toChar()) }
        val jsonString = builder.toString()
        val registryContent = json.decodeFromString<JsonRegistry>(jsonString)
        registryContent.words.forEachIndexed { index, word ->
            put(FileUri(map.size, wordMapper.map(word)))
        }
        registryContent.paragraphs.forEachIndexed { index, paragraph ->
            put(FileUri(index, paragraphMapper.map(paragraph)))
        }
    }

    fun interface ResourceUriMapper {
        fun map(resource: String): String
    }

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
