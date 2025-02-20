package org.pointyware.typing.data

import kotlinx.coroutines.delay
import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.serialization.json.Json


interface SubjectProviderFactory {
    suspend fun create(subjectSource: SubjectSource): SubjectProvider
}

/**
 *
 */
class SubjectProviderFactoryImpl(): SubjectProviderFactory {

    override suspend fun create(subjectSource: SubjectSource): SubjectProvider {
        return when(subjectSource) {
            is FileUri -> {
                val subjectList = loadStories(subjectSource.fileUriString)
                SubjectProviderImpl(
                    subjects = subjectList
                )
            }
        }
    }

    private fun loadStories(fileUriString: String): List<String> {
        val storyPath = fileUriString.substringAfter("file:")
        val source = SystemFileSystem.source(Path(storyPath))
        val readBuffer = Buffer()
        source.readAtMostTo(readBuffer, Long.MAX_VALUE)
        val storyString = readBuffer.readString()

        val story = Json.decodeFromString<JsonStory>(storyString)
        return story.paragraphs
    }
}

data class TestSubjectProviderFactory(
    var subjectProvider: SubjectProvider,
    var delay: Long = 0
): SubjectProviderFactory {
    override suspend fun create(subjectSource: SubjectSource): SubjectProvider {
        if (delay > 0) {
            delay(delay)
        }
        return subjectProvider
    }
}
