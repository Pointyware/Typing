package org.pointyware.typing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.pointyware.typing.viewmodels.TypingViewModelImpl

/**
 *
 */
@Composable
fun TypingApp(
    navController: NavHostController
) {
    val viewModel = remember {
        TypingViewModelImpl()
    }
    TypingScreen(
        viewModel = viewModel,
        modifier = Modifier
    )
}
