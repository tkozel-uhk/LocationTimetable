package cz.uhk.umte.tt.model

/**
 * Datová třída reprezentující výsledek API volání.
 */
data class ApiResult<T>(
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)