package org.pointyware.typing.data.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.typing.data.SubjectProviderFactory
import org.pointyware.typing.data.SubjectProviderFactoryImpl
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.data.TypingControllerImpl

val dataScope = named("data-scope")

/**
 *
 */
fun dataModule() = module {
    single<TypingController> {
        TypingControllerImpl(get(), get(qualifier = dataScope))
    }

    single<SubjectProviderFactory> {
        SubjectProviderFactoryImpl()
    }

    single<CoroutineScope>(qualifier = dataScope) {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}
