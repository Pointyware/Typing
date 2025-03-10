package org.pointyware.typing.android

import android.app.Application
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.pointyware.typing.di.sharedAppModule

/**
 */
class KoinApplication: Application() {
    @OptIn(ExperimentalResourceApi::class)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)

            modules (
                sharedAppModule(),
            )
        }
    }
}
