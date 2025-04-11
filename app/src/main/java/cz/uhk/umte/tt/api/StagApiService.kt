package cz.uhk.umte.tt.api

import cz.uhk.umte.tt.model.LocationResult
import cz.uhk.umte.tt.model.StagActivity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Rozhraní pro práci s rozvrhem místností
 */
interface StagApiService {
    @GET("getRozvrhByMistnost?budova=J&outputFormat=JSON")
    suspend fun getLocationActivities(@Query("mistnost") location: String): LocationResult
}