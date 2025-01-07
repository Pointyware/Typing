package org.pointyware.typing.data

import kotlin.random.Random
import kotlin.random.nextInt

/**
 *
 */
interface SubjectProvider {
    fun nextSubject(): String
}

class SubjectProviderImpl(
    val subjects: List<String>
): SubjectProvider {
    override fun nextSubject(): String {
        Random.nextInt(subjects.indices).let {
            return subjects[it]
        }
    }
}

data class TestSubjectProvider(
    var subject: String
): SubjectProvider {
    override fun nextSubject(): String {
        return subject
    }
}
