package org.pointyware.typing.interactors

import org.pointyware.typing.data.FileUri
import org.pointyware.typing.data.SubjectSourceRegistry

class LoadSubjectsUseCase(
    private val subjectSourceRegistry: SubjectSourceRegistry
) {

    suspend operator fun invoke(): Result<LoadedSubjects> {
        try {
            val vocabList = mutableListOf<FileUri>()
            val storiesList = mutableListOf<FileUri>()
            subjectSourceRegistry.getAll().forEach {
                when(it) {
                    is FileUri -> {
                        when {
                            it.fileUriString.contains("vocab") -> vocabList.add(it)
                            it.fileUriString.contains("paragraphs") -> storiesList.add(it)
                        }
                    }
                    else -> {
                        println("Unsupported subject source: $it")
                    }
                }
            }
            return Result.success(LoadedSubjects(vocabList, storiesList))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private suspend fun loadDirectoryFiles(idOffset: Int, files: List<String>): List<FileUri> {
        return files.mapIndexed { index, file ->
            FileUri(index + idOffset, file)
        }
    }
}

data class LoadedSubjects(
    val vocabList: List<FileUri>,
    val storiesList: List<FileUri>
)
