package org.pointyware.typing.navigation

import kotlinx.serialization.Serializable

/**
 * Defines top-level destinations in the app.
 */
sealed interface Screen {
    @Serializable
    data object MainMenu : Screen

    @Serializable
    data class Typing(
        /**
         * An integer id that uniquely identifies the subject source.
         */
        val subjectSourceId: Int
    ): Screen

    @Serializable
    data object Settings : Screen
}
