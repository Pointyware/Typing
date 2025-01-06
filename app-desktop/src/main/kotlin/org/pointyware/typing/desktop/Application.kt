package org.pointyware.typing.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.pointyware.typing.ui.TypingApp

/**
 *
 */
fun main(vararg args: String) = application {
    // startup logic

    val windowState = rememberWindowState()
    Window(
        state = windowState,
        title = "Typing!",
        onCloseRequest = ::exitApplication
    ) {
        TypingApp()
    }

    // shutdown logic
}
