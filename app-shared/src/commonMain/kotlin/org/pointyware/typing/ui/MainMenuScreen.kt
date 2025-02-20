package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.pointyware.typing.viewmodels.MainMenuUiState
import org.pointyware.typing.viewmodels.MainMenuViewModel

/**
 *
 */
@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel,
    onStartTyping: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.load()
    }
    MainMenuView(
        state = state,
        onSelectWords = viewModel::onSelectWords,
        onSelectParagraphs = viewModel::onSelectParagraphs,
        onStartTyping = onStartTyping,
    )
}

@Composable
fun MainMenuView(
    state: MainMenuUiState,
    modifier: Modifier = Modifier,
    onSelectWords: (index:Int) -> Unit,
    onSelectParagraphs: (index:Int) -> Unit,
    onStartTyping: (id:Int) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        var selectedWordIndex: Int? by remember { mutableStateOf(null) }
        ItemSelector(
            prompt = "Words",
            items = state.vocabList.map { it.fileUriString },
            onItemSelected = { selectedWordIndex = it; onSelectWords(it) },
            selectedItem = selectedWordIndex,
            onAction = action@ {
                val index = state.selectedVocab ?: return@action
                val id = state.vocabList.getOrNull(index)?.id ?: return@action
                onStartTyping(id)
            }
        )
        var selectedParagraphIndex: Int? by remember { mutableStateOf(null) }
        ItemSelector(
            prompt = "Paragraphs",
            items = state.storiesList.map { it.fileUriString },
            onItemSelected = { selectedParagraphIndex = it; onSelectParagraphs(it) },
            selectedItem = state.selectedVocab,
            onAction = action@ {
                val index = state.selectedParagraphs ?: return@action
                val id = state.storiesList.getOrNull(index)?.id ?: return@action
                onStartTyping(id)
            }
        )
    }
}
