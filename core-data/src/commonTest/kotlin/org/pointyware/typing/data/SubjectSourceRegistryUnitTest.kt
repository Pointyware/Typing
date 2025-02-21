package org.pointyware.typing.data

import kotlinx.coroutines.test.runTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.pointyware.typing.data.di.dataModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 */
class SubjectSourceRegistryUnitTest {

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                dataModule(),
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun registryLoadsFileContents() = runTest {
        SubjectSourceRegistry.loadFrom(
            Res.readBytes("files/directory.json"),
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
