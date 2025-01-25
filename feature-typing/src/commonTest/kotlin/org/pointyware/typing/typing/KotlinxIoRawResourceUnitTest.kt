package org.pointyware.typing.typing

import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * TODO: describe purpose/intent of KotlinxIoRawResourceUnitTest
 */
class KotlinxIoRawResourceUnitTest {


    @BeforeTest
    fun setUp() {

    }

    @AfterTest
    fun tearDown() {

    }

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun readResource_should_return_nonempty_string() {
        val pathString = Res.getUri("test-stories.json")
        val path = Path(pathString)

        val buffer = Buffer()
        val source = SystemFileSystem.source(path)
        val read = source.readAtMostTo(buffer, Long.MAX_VALUE)
        if (read > 0) {
            println("Read $read bytes")
        } else {
            println("Read nothing")
        }
        val jsonString = buffer.toString()
        val stories = Json.decodeFromString<JsonStory>(jsonString)
    }
}
