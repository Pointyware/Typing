package org.pointyware.typing.typing.di

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.pointyware.typing.data.SubjectProvider
import org.pointyware.typing.typing.GrimmSubjectProvider
import org.pointyware.typing.typing.Res


internal val stories_uri = named("stories-uri")

/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
fun typingModule() = module {
    single<SubjectProvider> {
        GrimmSubjectProvider(
            get<String>(qualifier = stories_uri)
        )
    }

    factory<String>(qualifier = stories_uri) {
        // Res.getUri("files/android-story.json")
        // Res.getUri("files/desktop-stories.json")
        Res.getUri("files/grimm-stories.json")
    }
}
