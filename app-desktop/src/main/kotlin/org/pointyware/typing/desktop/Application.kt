package org.pointyware.typing.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme

/**
 *
 */
fun main(vararg args: String) = application {
    // startup logic

    val navController = rememberNavController()

    val windowState = rememberWindowState()
    Window(
        state = windowState,
        title = "Typing!",
        onCloseRequest = ::exitApplication
    ) {
        TypingTheme {
            TypingApp(
                navController = navController
            )
        }
    }

    // shutdown logic
}
