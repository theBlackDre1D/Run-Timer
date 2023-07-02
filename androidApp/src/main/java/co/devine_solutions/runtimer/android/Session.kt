package co.devine_solutions.runtimer.android

import android.app.Application
import org.koin.core.context.startKoin

class Session : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
        }
    }
}