package cz.uhk.umte.tt.modules

import cz.uhk.umte.tt.api.StagApiService
import cz.uhk.umte.tt.repo.ActivityRepository
import cz.uhk.umte.tt.ui.activities.ActivitiesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Základní URL STAG WS pro retrofit
 */
const val STAG_URL = "https://stagws.uhk.cz/ws/services/rest2/rozvrhy/"

/**
 * Moduly pro viewmodely, repozitáře a retrofit.
 * Používá se v aplikační třídě TTApplication v inicializaci DI (Koin)
 */

val repositoryModule = module {
    single { ActivityRepository(get()) }
}

val networkModule = module {
    single {
        Retrofit.Builder() //konfigurace retrofitu
            .baseUrl(STAG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StagApiService::class.java)
    }
}

val viewModelsModule = module {
    viewModel { ActivitiesViewModel(get()) }
}