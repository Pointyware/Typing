package org.pointyware.typing.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.typing.navigation.Screen
import org.pointyware.typing.viewmodels.MainMenuViewModel
import org.pointyware.typing.viewmodels.TypingViewModel

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
        startDestination = Screen.MainMenu
    ) {
        composable<Screen.MainMenu> {
            val koin = remember { getKoin() }
            val viewModel = koin.get<MainMenuViewModel>()
            MainMenuScreen(
                viewModel = viewModel,
                onStartTyping = {
                    navController.navigate(Screen.Typing(subjectSourceId = it))
                }
            )
        }
        composable<Screen.Settings> {
            Text("Settings")
        }
        composable<Screen.Typing> {
            val arg = it.toRoute<Screen.Typing>()

            val koin = remember { getKoin() }
            val viewModel = koin.get<TypingViewModel>()
            LaunchedEffect(arg) {
                viewModel.setSubjectSource(arg.subjectSourceId)
            }
            TypingScreen(
                viewModel = viewModel,
                modifier = Modifier
            )
        }
    }
}
