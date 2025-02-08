package org.pointyware.typing.typing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 */
@Preview
@Composable
fun TypingFieldPreview(

) {
    var content by remember { mutableStateOf("") }
    TypingField(
        modifier = Modifier
            .background(Color.Red)
            .padding(8.dp),
        content = content,
        onCodePoint = { codePoint ->
            content += codePoint.toChar()
        },
        onDelete = {
            content = content.dropLast(1)
        },
        onEnter = {
            // simulate moving to next subject
            content = ""
        },
    )
}
