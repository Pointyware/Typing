package org.pointyware.typing.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.pointyware.typing.ui.TypingApp
import org.pointyware.typing.ui.theme.TypingTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val logTag = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = NavHostController(this)
        setContent {
            TypingTheme(
                dynamicTheme = true,
            ) {
                TypingApp(
                    navController
                )
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    override fun onResume() {
        super.onResume()

        val uri = Res.getUri("files/android-story.json")
        Log.i(logTag, "uri: $uri")

        val assetPath = uri.substringAfter("file:///android_asset/")
        assets.open(assetPath).use {
            val storyString = it.reader().readText()
            Log.i(logTag, "storyString: $storyString")
        }
    }
}
