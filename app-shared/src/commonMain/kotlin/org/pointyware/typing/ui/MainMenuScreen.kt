package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.pointyware.typing.data.FileUri
import org.pointyware.typing.data.SubjectSource
import org.pointyware.typing.data.storiesUri
import org.pointyware.typing.data.vocabUri
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
            onClick = { onStartTyping(vocabUri.id) },
        ) {
            Text("Words")
        }
        Button(
            onClick = { onStartTyping(storiesUri.id) },
        ) {
            Text("Paragraphs")
        }
    }
}
