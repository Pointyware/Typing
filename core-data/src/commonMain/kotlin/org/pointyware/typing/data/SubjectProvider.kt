package org.pointyware.typing.data

import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Provides blocks of text intended to be used as subjects for typing practice.
 */
interface SubjectProvider {
    fun nextSubject(): String
}

/**
 * A subject provider that randomly selects a subject from a given list.
 */
class SubjectProviderImpl(
    val subjects: List<String>
): SubjectProvider {
    override fun nextSubject(): String {
        return subjects[Random.nextInt(subjects.indices)]
    }
}

/**
 * A subject provider with an externally mutable subject.
 */
data class TestSubjectProvider(
    var subject: String
): SubjectProvider {
    override fun nextSubject(): String {
        return subject
    }
}
