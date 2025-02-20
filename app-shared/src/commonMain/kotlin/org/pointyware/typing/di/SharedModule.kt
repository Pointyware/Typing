package org.pointyware.typing.di

import kotlinx.coroutines.MainScope
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.typing.data.di.dataModule
import org.pointyware.typing.typing.di.typingModule
import org.pointyware.typing.viewmodels.MainMenuViewModel
import org.pointyware.typing.viewmodels.MainMenuViewModelImpl
import org.pointyware.typing.viewmodels.TypingViewModel
import org.pointyware.typing.viewmodels.TypingViewModelImpl

/**
 *
 */
fun sharedAppModule() = module {

    includes(
        sharedViewModelModule(),

        typingModule(),

        dataModule()
    )
}

fun sharedViewModelModule() = module {
    singleOf(::TypingViewModelImpl) {
        bind<TypingViewModel>()
    }

    single<TypingViewModel> {
        get<TypingViewModelImpl>()
    }

    singleOf(::MainMenuViewModelImpl) {
        bind<MainMenuViewModel>()
    }
}
