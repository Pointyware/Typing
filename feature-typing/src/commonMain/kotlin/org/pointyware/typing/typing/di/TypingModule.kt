package org.pointyware.typing.typing.di

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.dsl.module
import org.pointyware.typing.data.di.stories_uri
import org.pointyware.typing.typing.Res


/**
 *
 */
@OptIn(ExperimentalResourceApi::class)
fun typingModule() = module {
    factory<String>(qualifier = stories_uri) {
        // Res.getUri("files/android-story.json")
        // Res.getUri("files/desktop-stories.json")
        Res.getUri("files/grimm-stories.json")
    }
}
