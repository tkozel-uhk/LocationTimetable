package cz.uhk.umte.tt.model

import com.google.gson.annotations.SerializedName

/**
 * Datová třída reprezentující rozvrhovou aktivitu (část výstupního JSONu).
 */
data class StagActivity(
    @SerializedName("predmet")
    val name: String,
    @SerializedName("nazev")
    val description: String,
    @SerializedName("vsichniUciteleJmenaTituly")
    val teacher: String,
    @SerializedName("typAkce")
    val type: String
)
