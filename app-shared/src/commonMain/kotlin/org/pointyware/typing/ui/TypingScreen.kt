package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays some subject text for the user to type and provides a space where the user can type.
 */
@Composable
fun TypingScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Typing Subject",
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = "Typing Subject",
            onValueChange = {},
            label = { Text("Type Here") }
        )
    }
}
