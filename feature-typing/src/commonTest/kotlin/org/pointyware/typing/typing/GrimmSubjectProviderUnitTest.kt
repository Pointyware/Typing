package org.pointyware.typing.typing

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 *
 */
class GrimmSubjectProviderUnitTest {

    private lateinit var subjectProvider: GrimmSubjectProvider

    @BeforeTest
    fun setUp() {
        subjectProvider = GrimmSubjectProvider()
    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun nextSubject_should_return_nonempty_string() {
        repeat(10) {
            val subject = subjectProvider.nextSubject()
            assertTrue(subject.isNotEmpty())
        }
    }

    @Test
    fun nextSubject_should_return_a_string_unique_from_previous() {
        val lastSubject: String? = null
        repeat(10) {
            val subject = subjectProvider.nextSubject()
            assertTrue(subject != lastSubject)
        }
    }
}
