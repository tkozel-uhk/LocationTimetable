package cz.uhk.umte.tt.repo

import cz.uhk.umte.tt.api.StagApiService
import cz.uhk.umte.tt.model.StagActivity
import cz.uhk.umte.tt.model.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Repository rozvrhových aktivit
 * Používá Retrofit k získání dat z API a flow k předávání dat do ViewModelu
 */
class ActivityRepository(private val apiService: StagApiService) {
    /**
     * Vrací flow obsahující ApiResult se seznamem aktivit pro zadanou místnost
     * @param location místnost (např. J1
     */
    fun getActivities(location: String): Flow<ApiResult<List<StagActivity>>> = flow {
        emit(ApiResult(isLoading = true))
        try {
            val activities = apiService.getLocationActivities(location).activities
                .filter { it.type in listOf("Přednáška", "Cvičení") } //chceme jen P/C
                .sortedBy { it.description } //řazíme podle popisu/názvu předmětu
            emit(ApiResult(data = activities)) //emitujeme data
        } catch (e: Exception) {
            emit(ApiResult(error = e.localizedMessage ?: "Nastala neznámá chyba")) //emitujeme chybu
        }
    }.flowOn(Dispatchers.IO) //specifikujeme, že se bude provádět na IO vlákně, tj. mimo UI vlákno
}