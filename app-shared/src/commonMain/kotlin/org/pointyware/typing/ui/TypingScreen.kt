package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import org.jetbrains.compose.resources.stringResource
import org.pointyware.typing.shared.Res
import org.pointyware.typing.shared.label_wpm
import org.pointyware.typing.typing.ui.TypingField
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.ui.theme.mediumPadding
import org.pointyware.typing.viewmodels.TypingUiState
import org.pointyware.typing.viewmodels.TypingViewModel

/**
 * Displays some subject text for the user to type and provides a space where the user can type.
 */
@Composable
fun TypingScreen(
    viewModel: TypingViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    TypingView(
        state = state,
        modifier = modifier.fillMaxSize(),
        onCodePoint = viewModel::onKeyStroke,
        onDelete = viewModel::onDelete,
        onReset = viewModel::onReset
    )
}

@Composable
fun TypingView(
    state: TypingUiState,
    modifier: Modifier = Modifier,
    onCodePoint: (Int) -> Boolean,
    onDelete: () -> Unit,
    onReset: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = stringResource(Res.string.label_wpm, state.progress.wpm))
        Timer(state.timerState)
        val annotatedText = remember(state.progress) {
            buildAnnotatedString {
                append(state.subject)
                state.progress.incorrect.forEach {
                    addStyle(style = SpanStyle(background = Color.Red), it.first, it.last + 1)
                }
            }
        }
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(TypingTheme.mediumPadding)
            ,
            text = annotatedText,
        )
        TypingField(
            content = state.progress.string,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onCodePoint = { codePoint ->
                onCodePoint(codePoint)
            },
            onDelete = {
                onDelete()
            },
            onEnter = {
                onReset()
            },
        )
    }
}
