package org.pointyware.typing.viewmodels

import org.pointyware.typing.data.FileUri

data class MainMenuUiState(
    val selectedVocab: FileUri,
    val selectedParagraphs: FileUri,
)
