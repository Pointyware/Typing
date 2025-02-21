package org.pointyware.typing.data

import kotlinx.coroutines.test.runTest
import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.shared.Res
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 */
@OptIn(ExperimentalResourceApi::class)
class KotlinxIoComposeResFilesUnitTest {

    @Test
    fun read_file_bytes() = runTest {
        val resourcePath = "files/someFile"
        val resourceBytes = Res.readBytes(resourcePath)
        StringBuilder().apply {
            resourceBytes.forEach { append(it.toChar()) }
            assertEquals("These are the contents of the file.\n", toString())
        }
    }

    @Test
    fun read_file_string() {
        val resourcePath = "files/someFile"
        val resourceUriString = Res.getUri(resourcePath)
        val filePathString = resourceUriString.substringAfter("file:")
        val filePath = Path(filePathString)
        assertFalse(filePathString.contains(".jar"))

        val rawSource = SystemFileSystem.source(filePath)
        val buffer = Buffer()
        val bytesRead = rawSource.readAtMostTo(buffer, Long.MAX_VALUE)
        assertTrue(bytesRead > 0)

        val rawString = buffer.readString()
        assertEquals("These are the contents of the file.\n", rawString)
    }
}
