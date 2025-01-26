package org.pointyware.typing.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.typing.GrimmSubjectProvider
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
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
                GrimmSubjectProvider(Res.getUri("files/desktop-stories.json")),
                navController = navController
            )
        }
    }

    // shutdown logic
}
