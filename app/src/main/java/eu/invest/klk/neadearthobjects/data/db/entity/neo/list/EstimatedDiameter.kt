package eu.invest.klk.neadearthobjects.data.db.entity.neo.list

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class EstimatedDiameter(
    @SerializedName("kilometers")
    @Embedded(prefix = "kilometer_")
    val kilometers: Kilometers,
    @SerializedName("miles")
    @Embedded(prefix = "mile_")
    val miles: Miles
)