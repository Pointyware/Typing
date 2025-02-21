package org.pointyware.typing.interactors

import org.pointyware.typing.data.FileUri

class LoadSubjectsUseCase {

    suspend operator fun invoke(): Result<LoadedSubjects> {
        try {
            val vocabList = loadDirectoryFiles(idOffset = 0, listOf(
                "files/words/vocab.json"
            ))
            val storiesList = loadDirectoryFiles(idOffset = vocabList.size, listOf(
                "files/paragraphs/desktop-stories.json",
                "files/paragraphs/grimm-stories.json",
            ))
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
