package org.pointyware.typing.desktop

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.typing.data.SubjectSourceRegistry
import org.pointyware.typing.di.sharedAppModule
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme
import org.pointyware.typing.shared.Res as SharedRes

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
fun main(vararg args: String) = application {
    // startup logic

    startKoin {
        modules(
            sharedAppModule(),
        )
    }

    val subjectSourceRegistry = getKoin().get<SubjectSourceRegistry>()

    LaunchedEffect(Unit) {
        println("Loading Registry")
        subjectSourceRegistry.loadFrom(
            byteLoader = { SharedRes.readBytes("files/available-files.json") },
            wordMapper = { SharedRes.getUri("files/words/$it.json") },
            paragraphMapper = { SharedRes.getUri("files/paragraphs/$it.json") }
        )
        println("Registry Loaded")
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
