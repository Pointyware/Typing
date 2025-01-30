package org.pointyware.typing.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.typing.viewmodels.TypingViewModel
import org.pointyware.typing.viewmodels.TypingViewModelImpl

/**
 *
 */
fun sharedAppModule() = module {

    includes(
        sharedViewModelModule()
    )
}

fun sharedViewModelModule() = module {
    singleOf(::TypingViewModelImpl) {
        bind<TypingViewModel>()
    }

}
