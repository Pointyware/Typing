package org.pointyware.typing.data

import kotlin.random.Random
import kotlin.random.nextInt

/**
 *
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

data class TestSubjectProvider(
    var subject: String
): SubjectProvider {
    override fun nextSubject(): String {
        return subject
    }
}
