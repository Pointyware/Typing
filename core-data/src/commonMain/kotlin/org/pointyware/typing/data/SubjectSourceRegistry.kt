package org.pointyware.typing.data

import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin
import kotlin.coroutines.CoroutineContext

interface SubjectSourceRegistry {
    suspend fun loadFrom(
        byteLoader: suspend ()->ByteArray,
        wordMapper: ResourceUriMapper,
        paragraphMapper: ResourceUriMapper
    )
    fun put(subjectSource: SubjectSource)
    fun get(id: Int): SubjectSource?
    fun getAll(): List<SubjectSource>
}

fun interface ResourceUriMapper {
    fun map(resource: String): String
}

/**
 * A registry of available subjects.
 */
class SubjectSourceRegistryImpl(
    private val dataContext: CoroutineContext
): SubjectSourceRegistry { // TODO: load list of json files and register them on app startup

    private val map: MutableMap<Int, SubjectSource> = mutableMapOf()

    private val json = getKoin().get<Json>()

    override suspend fun loadFrom(
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

    override fun put(subjectSource: SubjectSource) {
        map[subjectSource.id] = subjectSource
    }

    override fun get(id: Int): SubjectSource? {
        return map[id]
    }

    override fun getAll(): List<SubjectSource> {
        return map.values.toList()
    }
}
