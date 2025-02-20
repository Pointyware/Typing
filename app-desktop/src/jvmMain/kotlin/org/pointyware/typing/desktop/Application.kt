package org.pointyware.typing.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.pointyware.typing.di.sharedAppModule
import org.pointyware.typing.data.di.stories_uri
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
fun main(vararg args: String) = application {
    // startup logic

    startKoin {
        modules(
            sharedAppModule(),

            module {
                factory<String>(qualifier = stories_uri) {
                    Res.getUri("files/desktop-stories.json")
                }
            }
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
