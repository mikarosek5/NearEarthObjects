package eu.invest.klk.neadearthobjects.data.db.entity.neo.list

import com.google.gson.annotations.SerializedName

data class Miles(
    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double,
    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double
)