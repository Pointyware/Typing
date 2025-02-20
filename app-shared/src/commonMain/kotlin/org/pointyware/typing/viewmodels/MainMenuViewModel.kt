package org.pointyware.typing.viewmodels

import androidx.lifecycle.ViewModel

/**
 * Represents a UI state for the main menu screen.
 */
interface MainMenuViewModel {
    fun onSelectWords()
    fun onSelectParagraphs()
}

/**
 *
 */
class MainMenuViewModelImpl(

): ViewModel(), MainMenuViewModel {

    override fun onSelectWords() {
        TODO("Not yet implemented")
    }

    override fun onSelectParagraphs() {
        TODO("Not yet implemented")
    }
}
