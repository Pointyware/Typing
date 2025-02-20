package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.pointyware.typing.viewmodels.MainMenuViewModel

/**
 *
 */
@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel,
    onStartTyping: (Int) -> Unit,
) {
    Column {
        Button(
            onClick = { onStartTyping(0) }, // TODO: replace with ui state
        ) {
            Text("Words")
        }
        Button(
            onClick = { onStartTyping(1) }, // TODO: replace with ui state
        ) {
            Text("Paragraphs")
        }
    }
}
