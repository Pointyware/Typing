package org.pointyware.typing.interactors

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.data.FileUri
import org.pointyware.typing.shared.Res

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

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun loadDirectoryFiles(idOffset: Int, files: List<String>): List<FileUri> {
        return files.mapIndexed { index, file ->
            val contents = Res.readBytes(file)
            val stringBuilder = StringBuilder().apply {
                contents.forEach { append(it.toChar()) }
            }
            FileUri(index + idOffset, stringBuilder.toString())
        }
    }
}

data class LoadedSubjects(
    val vocabList: List<FileUri>,
    val storiesList: List<FileUri>
)
