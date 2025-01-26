package org.pointyware.typing.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import org.pointyware.typing.typing.GrimmSubjectProvider
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = NavHostController(this)
        setContent {
            TypingTheme(
                dynamicTheme = true,
            ) {
                TypingApp(
                    GrimmSubjectProvider(Res.getUri("files/android-story.json")),
                    navController
                )
            }
        }
    }
}
