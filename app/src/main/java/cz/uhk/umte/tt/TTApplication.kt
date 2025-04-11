package cz.uhk.umte.tt

import android.app.Application
import cz.uhk.umte.tt.modules.networkModule
import cz.uhk.umte.tt.modules.repositoryModule
import cz.uhk.umte.tt.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Aplikační třída pro inicializaci DI / Koin
 */
class TTApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Inicializace DI
         */
        startKoin {
            androidContext(this@TTApplication)
            androidLogger(Level.DEBUG)
            modules(networkModule, repositoryModule, viewModelsModule)
        }
    }
}