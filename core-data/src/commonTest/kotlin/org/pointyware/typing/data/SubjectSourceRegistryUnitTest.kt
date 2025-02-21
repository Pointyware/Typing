package org.pointyware.typing.data

import kotlinx.coroutines.test.runTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 */
class SubjectSourceRegistryUnitTest {

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun registryLoadsFileContents() = runTest {
        SubjectSourceRegistry.loadFrom(
            Res.readBytes(""),
            { Res.getUri("files/words/$it.json") },
            { Res.getUri("files/paragraphs/$it.json") }
        )

        assertEquals(FileUri(0, "files/words/alpha.json"), SubjectSourceRegistry.get(0))
        assertEquals(FileUri(1, "files/words/beta.json"), SubjectSourceRegistry.get(1))
        assertEquals(FileUri(2, "files/words/delta.json"), SubjectSourceRegistry.get(2))
        assertEquals(FileUri(3, "files/paragraphs/gamma.json"), SubjectSourceRegistry.get(3))
        assertEquals(FileUri(4, "files/paragraphs/phi.json"), SubjectSourceRegistry.get(4))
        assertEquals(FileUri(5, "files/paragraphs/theta.json"), SubjectSourceRegistry.get(5))
    }
}
