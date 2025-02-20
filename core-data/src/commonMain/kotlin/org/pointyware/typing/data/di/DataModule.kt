package org.pointyware.typing.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.typing.data.GrimmSubjectProvider
import org.pointyware.typing.data.SubjectProvider
import org.pointyware.typing.data.SubjectProviderImpl
import org.pointyware.typing.data.TypingController
import org.pointyware.typing.data.TypingControllerImpl

val stories_uri = named("stories-uri")

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
}
