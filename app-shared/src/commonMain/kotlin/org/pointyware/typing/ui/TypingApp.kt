package org.pointyware.typing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.pointyware.typing.data.TestSubjectProvider
import org.pointyware.typing.data.TypingControllerImpl
import org.pointyware.typing.viewmodels.TypingViewModelImpl

/**
 *
 */
@Composable
fun TypingApp(
    navController: NavHostController
) {
    val viewModel = remember {
        val subjectProvider = TestSubjectProvider("The quick brown fox jumps over the lazy dog.")
        val controller = TypingControllerImpl(subjectProvider)
        TypingViewModelImpl(controller)
    }
    TypingScreen(
        viewModel = viewModel,
        modifier = Modifier
    )
}
