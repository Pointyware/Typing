package org.pointyware.typing.data

import kotlinx.coroutines.test.runTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.typing.data.di.dataModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 */
class SubjectSourceRegistryUnitTest {

    private lateinit var registry: SubjectSourceRegistry

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                dataModule(),
            )
        }

        registry = getKoin().get()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun registryLoadsFileContents() = runTest {
        registry.loadFrom(
            { Res.readBytes("files/directory.json") },
            { Res.getUri("files/words/$it.json") },
            { Res.getUri("files/paragraphs/$it.json") }
        )

        val expectedSuffixes = listOf(
            "files/words/alpha.json",
            "files/words/beta.json",
            "files/words/delta.json",
            "files/paragraphs/gamma.json",
            "files/paragraphs/phi.json",
            "files/paragraphs/theta.json"
        )
        (0..5).forEach {
            val fileUri = registry.get(it) as FileUri

            assertEquals(it, fileUri.id)
            assertTrue(fileUri.fileUriString.endsWith(expectedSuffixes[it]))
            assertTrue(fileUri.fileUriString.startsWith("file:"))
        }
    }
}
