package org.pointyware.typing.typing

import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.data.SubjectProvider

/**
 * Retrieves a Brothers Grimm fairy tale as the subject for the user to type.
 */
class GrimmSubjectProvider: SubjectProvider {

    private var storyQueue = mutableListOf<String>()

    override fun nextSubject(): String {
        if (storyQueue.isEmpty()) {
            loadStory()
        }
        return storyQueue.removeAt(0)
    }

    @OptIn(ExperimentalResourceApi::class)
    private fun loadStory() {
        val storyUri = Res.getUri("files/grimm-stories.json")
        val storyPath = storyUri.substringAfter("file:")
        val source = SystemFileSystem.source(Path(storyPath))
        val readBuffer = Buffer()
        source.readAtMostTo(readBuffer, Long.MAX_VALUE)
        val storyString = readBuffer.readString()

        val story = Json.decodeFromString<JsonStory>(storyString)
        storyQueue.addAll(story.paragraphs)
    }
}

@Serializable
data class JsonStory(
    val title: String,
    val paragraphs: List<String>
)
