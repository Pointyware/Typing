package org.pointyware.typing.typing.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.unit.dp

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
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    Surface(
        modifier = modifier
    ) {
        Text(
            text = content,
            modifier = Modifier
                .focusRequester(focusRequester)
                .focusable(interactionSource = interactionSource)
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
                                    val codePoint = event.utf16CodePoint.toChar()
                                    if (codePoint.isLetter())
                                        onCodePoint(event.utf16CodePoint)
                                    true
                                }
                            }
                        }
                        else -> false
                    }
                }
                .clickable {
                    focusRequester.requestFocus()
                }
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color.Blue else Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
        )
    }
}
