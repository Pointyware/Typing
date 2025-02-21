package org.pointyware.typing.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.pointyware.typing.data.FileUri
import org.pointyware.typing.data.SubjectSourceRegistry
import org.pointyware.typing.di.sharedAppModule
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
fun main(vararg args: String) = application {
    // startup logic

    with(SubjectSourceRegistry) {
        put(FileUri(0, org.pointyware.typing.shared.Res.getUri("files/words/vocab.json")))
        put(FileUri(1, org.pointyware.typing.shared.Res.getUri("files/paragraphs/grimm-stories.json")))
        put(FileUri(2, org.pointyware.typing.shared.Res.getUri("files/paragraphs/desktop-stories.json")))
    }

    startKoin {
        modules(
            sharedAppModule(),
        )
    }

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
