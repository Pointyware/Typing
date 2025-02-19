package org.pointyware.typing.navigation

import kotlinx.serialization.Serializable
import org.pointyware.typing.data.SubjectSource

/**
 * Defines top-level destinations in the app.
 */
sealed interface Screen {
    @Serializable
    data object MainMenu : Screen

    @Serializable
    data class Typing(
        val subjectSource: SubjectSource
    ): Screen

    @Serializable
    data object Settings : Screen
}
