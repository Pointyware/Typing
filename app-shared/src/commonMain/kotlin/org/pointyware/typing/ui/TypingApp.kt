package org.pointyware.typing.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.typing.navigation.Screen
import org.pointyware.typing.viewmodels.TypingViewModel
import org.pointyware.typing.viewmodels.TypingViewModelImpl

/**
 *
 */
@Composable
fun TypingApp(
    navController: NavHostController
) {

    // TODO: add scaffold

    NavHost(
        navController = navController,
        startDestination = Screen.Typing
    ) {
        composable<Screen.MainMenu> {
            Text("Main Menu")
        }
        composable<Screen.Settings> {
            Text("Settings")
        }
        composable<Screen.Typing> {
            val koin = getKoin()
            val viewModel = koin.get<TypingViewModel>()
            TypingScreen(
                viewModel = viewModel,
                modifier = Modifier
            )
        }
    }
}
