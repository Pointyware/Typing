package org.pointyware.typing.data

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

/**
 */
class SubjectSourceRegistryUnitTest {

    @Test
    fun registryLoadsFileContents() = runTest {
        SubjectSourceRegistry.loadFrom(
            Res.readBytes(""),
            { Res.getUri("files/words/$it.json") },
            { Res.getUri("files/paragraphs/$it.json") }
        )
    }
}
