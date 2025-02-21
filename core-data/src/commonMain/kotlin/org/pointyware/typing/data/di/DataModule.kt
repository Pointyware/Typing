package org.pointyware.typing.data.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.pointyware.typing.data.SubjectProviderFactory
import org.pointyware.typing.data.SubjectProviderFactoryImpl
import org.pointyware.typing.data.SubjectSourceRegistry
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.data.TypingControllerImpl

val dataScope = named("data-scope")
val Scope.dataCoroutineScope get() = get<CoroutineScope>(qualifier = dataScope)

/**
 *
 */
fun dataModule() = module {
    single<TypingController> {
        TypingControllerImpl(get(), dataCoroutineScope)
    }

    singleOf(::SubjectSourceRegistry)

    single<SubjectProviderFactory> {
        SubjectProviderFactoryImpl()
    }

    single<CoroutineScope>(qualifier = dataScope) {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
}
