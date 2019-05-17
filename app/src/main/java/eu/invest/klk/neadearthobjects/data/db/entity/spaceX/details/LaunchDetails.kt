package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class LaunchDetails(
    @SerializedName("launches")
    val launches: List<Launche>
)