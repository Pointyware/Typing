package org.pointyware.typing.data

import kotlinx.coroutines.delay


interface SubjectProviderFactory {
    suspend fun create(subjectSource: SubjectSource): SubjectProvider
}

/**
 *
 */
class SubjectProviderFactoryImpl(): SubjectProviderFactory {

    override suspend fun create(subjectSource: SubjectSource): SubjectProvider {
        return when(subjectSource) {
            is FileUri -> {
                val subjectList = TODO(); subjectSource.fileUriString
                SubjectProviderImpl(
                    subjects = subjectList
                )
            }
        }
    }
}

data class TestSubjectProviderFactory(
    var subjectProvider: SubjectProvider,
    var delay: Long = 0
): SubjectProviderFactory {
    override suspend fun create(subjectSource: SubjectSource): SubjectProvider {
        if (delay > 0) {
            delay(delay)
        }
        return subjectProvider
    }
}
