package org.pointyware.typing.typing.ui

import androidx.compose.foundation.focusable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.utf16CodePoint

/**
 * Provides a space to type text. Similar to a [TextField] but with fewer text controls. Text can
 * only be modified one character at a time either through entry or deletion. Copy-paste and all
 * other text utilities provided by TextField are not available.
 *
 * @param onCodePoint Invoked when a single character/codepoint is entered.
 * @param onDelete Invoked when a character is deleted.
 * @param onEnter Invoked when the enter/return key is pressed.
 */
@Composable
fun TypingField(
    content: String,
    onCodePoint: (Int) -> Unit,
    onDelete: () -> Unit,
    onEnter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
    ) {
        Text(
            text = content,
            modifier = modifier
                .onKeyEvent { event ->
                    when {
                        event.type == KeyEventType.KeyDown -> {
                            when (event.key) {
                                Key.Enter -> {
                                    onEnter()
                                    true
                                }
                                Key.Backspace -> {
                                    onDelete()
                                    true
                                }
                                else -> {
                                    onCodePoint(event.utf16CodePoint)
                                    true
                                }
                            }
                        }
                        else -> false
                    }
                }
        )
    }
}
