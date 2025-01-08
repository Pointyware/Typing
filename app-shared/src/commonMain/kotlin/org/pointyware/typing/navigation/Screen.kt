package org.pointyware.typing.navigation

/**
 */
sealed interface Screen {
    @Serializable
    data object MainMenu : Screen

    @Serializable
    data object Typing : Screen

    @Serializable
    data object Settings : Screen
}
