package org.pointyware.typing.typing.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.typing.data.SubjectProvider
import org.pointyware.typing.typing.GrimmSubjectProvider

/**
 *
 */
fun typingModule() = module {
    singleOf(::GrimmSubjectProvider) {
        bind<SubjectProvider>()
    }
}
