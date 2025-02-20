package org.pointyware.typing.viewmodels

import org.pointyware.typing.data.FileUri

data class MainMenuUiState(
    val vocabList: List<FileUri>,
    val selectedVocab: FileUri?,
    val storiesList: List<FileUri>,
    val selectedParagraphs: FileUri?,
) {
    companion object {
        val Empty = MainMenuUiState(
            vocabList = emptyList(),
            selectedVocab = null,
            storiesList = emptyList(),
            selectedParagraphs = null,
        )
    }
}
