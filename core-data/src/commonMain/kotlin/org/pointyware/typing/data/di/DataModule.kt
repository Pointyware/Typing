package org.pointyware.typing.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.data.TypingControllerImpl

/**
 *
 */
fun dataModule() = module {
    singleOf(::TypingControllerImpl) {
        bind<TypingController>()
    }
}
