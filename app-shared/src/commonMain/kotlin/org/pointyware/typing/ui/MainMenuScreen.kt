package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    onSelectWords: (Int) -> Unit,
    onSelectParagraphs: (Int) -> Unit,
    onStartTyping: (Int) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = { onStartTyping(state.selectedVocab?.id ?: 0) },
            enabled = state.selectedVocab != null,
        ) {
            Text("Words")
        }
        Button(
            onClick = { onStartTyping(state.selectedParagraphs?.id ?: 0) },
            enabled = state.selectedParagraphs != null,
        ) {
            Text("Paragraphs")
        }
    }
}
