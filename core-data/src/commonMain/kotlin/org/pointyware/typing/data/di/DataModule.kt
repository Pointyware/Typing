package org.pointyware.typing.data.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.typing.data.GrimmSubjectProvider
import org.pointyware.typing.data.SubjectProvider
import org.pointyware.typing.data.SubjectProviderImpl
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.data.TypingControllerImpl

val stories_uri = named("stories-uri")

val dataScope = named("data-scope")

/**
 *
 */
fun dataModule() = module {
    singleOf(::TypingControllerImpl) {
        bind<TypingController>()
    }

    single<TypingController> {
        val subjectProviderFactory = SubjectProviderImpl(listOf("Replace me. Dear GOD please replace me!"))
        get<TypingControllerImpl> {
            parametersOf(subjectProviderFactory)
        }
    }

    single<SubjectProvider> {
        GrimmSubjectProvider(
            get<String>(qualifier = stories_uri)
        )
    }

    single<CoroutineScope>(qualifier = dataScope) {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}
