package org.pointyware.typing.interactors

import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.data.FileUri
import org.pointyware.typing.shared.Res

class LoadSubjectsUseCase {

    operator fun invoke(): Result<LoadedSubjects> {
        try {
            val vocabList = loadDirectoryFiles("files/words", idOffset = 0)
            val storiesList = loadDirectoryFiles("files/paragraphs", idOffset = vocabList.size)
            return Result.success(LoadedSubjects(vocabList, storiesList))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    private fun loadDirectoryFiles(directory: String, idOffset: Int): List<FileUri> {
        val resolvedDirectory = Res.getUri(directory).substringAfter("file:")
        return SystemFileSystem.list(Path(resolvedDirectory)).mapIndexed { index, file ->
            FileUri(index + idOffset, SystemFileSystem.resolve(file).toString())
        }
    }
}

data class LoadedSubjects(
    val vocabList: List<FileUri>,
    val storiesList: List<FileUri>
)
