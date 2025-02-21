package org.pointyware.typing.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin
import kotlin.coroutines.CoroutineContext

/**
 * A registry of available subjects.
 */
class SubjectSourceRegistry(
    private val dataContext: CoroutineContext
) { // TODO: load list of json files and register them on app startup

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()

    private val json = getKoin().get<Json>()

    suspend fun loadFrom(
        byteLoader: suspend ()->ByteArray,
        wordMapper: ResourceUriMapper,
        paragraphMapper: ResourceUriMapper
    ) = withContext(dataContext) {
        val fileBytes = byteLoader()
        val jsonString = fileBytes.decodeToString()
        val registryContent = json.decodeFromString<JsonRegistry>(jsonString)
        registryContent.words.forEachIndexed { index, word ->
            put(FileUri(map.size, wordMapper.map(word)))
        }
        registryContent.paragraphs.forEachIndexed { index, paragraph ->
            put(FileUri(map.size, paragraphMapper.map(paragraph)))
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
