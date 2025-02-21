package org.pointyware.typing.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 *
 */
class TypingControllerTest {


    private lateinit var factory: TestSubjectProviderFactory
    private lateinit var subjectProvider: TestSubjectProvider
    private lateinit var controller: TypingController

    @BeforeTest
    fun setUp() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        val testCoroutineScope = CoroutineScope(testDispatcher)
        subjectProvider = TestSubjectProvider("")
        factory = TestSubjectProviderFactory(subjectProvider, delay = 0)
        controller = TypingControllerImpl(subjectProviderFactory = factory, testCoroutineScope)
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
            string = "the quick borwn fox jumped over the laztdog",
            incorrect = listOf(11..12, 39..42),
            accuracy = 0.0f,
            wpm = 0.0f
        )
        assertEquals(expected.incorrect, result.incorrect)
    }

    @Test
    fun incorrect_input_should_generate_incorrect_ranges_2() {
        /*
        Given: the typing controller has a subject
        And: the progress state is observed
         */
        subjectProvider.subject = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        controller.reset()
        val progress = controller.progress

        /*
        When: the user types an incorrect character
         */
        "ZYXWVUTSRQPONMLKJIHGFEDCBA".forEach {
            controller.consume(it)
        }

        /*
        Then: the progress should contain the incorrect range
         */
        val result = progress.value
        val expected = TypingProgress(
            "ZYXWVUTSRQPONMLKJIHGFEDCBA",
            listOf(0 until 26),
            accuracy = 0.0f,
            wpm = 0.0f
        )
        assertEquals(expected.incorrect, result.incorrect)
    }

    @Test
    fun incorrect_input_should_generate_incorrect_ranges_3() {
        /*
        Given: the typing controller has a subject
        And: the progress state is observed
         */
        subjectProvider.subject = "the quick brown fox jumped over the lazy dog"
        controller.reset()
        val progress = controller.progress
        // "the quick brown fox jumped over the lazy dog"
        // "How does this not work?"
        // "01234567890123456789012345678901234567890"

        /*
        When: the user types an incorrect input
         */
        controller.setInput("How does this not work?")

        /*
        Then: the progress should contain the incorrect range
         */
        val result = progress.value
        val expected = TypingProgress(
            "How does this not work?",
            listOf(
                0 .. 2,
                4 .. 13,
                15 .. 22,
            ),
            accuracy = 0.0f,
            wpm = 0.0f
        )
        assertEquals(expected.incorrect, result.incorrect)
    }
}
