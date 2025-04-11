package cz.uhk.umte.tt.ui.activities

import cz.uhk.umte.tt.model.ApiResult
import cz.uhk.umte.tt.model.StagActivity

/**
 * Datová třída reprezentující stav ActivityScreenu
 */
data class ActivitiesUiState(
    val locationInput: String = "", //místnost zadaná uživatelem
    val activitiesResult: ApiResult<List<StagActivity>> = ApiResult() //výsledek volání API
)