package cz.uhk.umte.tt.model

import com.google.gson.annotations.SerializedName

/**
 * Třída se seznamem rozvrhových aktivit (v dané mistnosti).
 */
class LocationResult(@SerializedName("rozvrhovaAkce") val activities : List<StagActivity>)
