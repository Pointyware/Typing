package org.pointyware.typing.viewmodels

import org.pointyware.typing.data.FileUri
import org.pointyware.typing.ui.LoadingState

data class MainMenuUiState(
    val vocabList: List<FileUri>,
    val selectedVocab: FileUri?,
    val storiesList: List<FileUri>,
    val selectedParagraphs: FileUri?,
    val loadingState: LoadingState
) {
    companion object {
        val Empty = MainMenuUiState(
            vocabList = emptyList(),
            selectedVocab = null,
            storiesList = emptyList(),
            selectedParagraphs = null,
            loadingState = LoadingState.Idle
        )
    }
}
