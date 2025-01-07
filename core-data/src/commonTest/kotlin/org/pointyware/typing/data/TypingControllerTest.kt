package org.pointyware.typing.data

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 *
 */
class TypingControllerTest {


    private lateinit var subjectProvider: TestSubjectProvider
    private lateinit var controller: TypingController

    @BeforeTest
    fun setUp() {
        subjectProvider = TestSubjectProvider("")
        controller = TypingControllerImpl(subjectProvider)
    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun incorrect_input_should_generate_incorrect_ranges() {
        /*
        Given: the typing controller has a subject
        And: the progress state is observed
         */
        subjectProvider.subject = "the quick brown fox jumped over the lazy dog"
        controller.reset()
        val progress = controller.progress

        /*
        When: the user types an incorrect character
         */
        "the quick borwn fox jumped over the laztdog".forEach {
            controller.consume(it)
        }

        /*
        Then: the progress should contain the incorrect range
         */
        val result = progress.value
        val expected = TypingProgress(
            "the quick borwn fox jumped over the laztdog",
            listOf(11..12, 39..42)
        )
        assertEquals(expected, result)
    }
}
