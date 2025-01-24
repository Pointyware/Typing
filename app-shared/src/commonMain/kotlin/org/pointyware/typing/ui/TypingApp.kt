package org.pointyware.typing.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.pointyware.typing.data.TestSubjectProvider
import org.pointyware.typing.data.TypingControllerImpl
import org.pointyware.typing.navigation.Screen
import org.pointyware.typing.typing.GrimmSubjectProvider
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
            val viewModel = remember {
                val subjectProvider = TestSubjectProvider(
                    "There lived in a certain village a poor old woman who had collected a mess of beans, and was going to cook them. So she made a fire on her hearth, and, in order to make it bmm better, she put in a handful of straw. When the beans began to bubble in the pot, one of them fell out and lay, never noticed, near a straw which was already there; soon a red-hot coal jumped out of the fire and joined the pair."
                ) // GrimmSubjectProvider()
                val controller = TypingControllerImpl(subjectProvider)
                controller.reset()
                TypingViewModelImpl(controller)
            }
            TypingScreen(
                viewModel = viewModel,
                modifier = Modifier
            )
        }
    }
}
