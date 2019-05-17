package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Pad(
    @SerializedName("agencies")
    val agencies: List<Agency>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("infoURL")
    val infoURL: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("mapURL")
    val mapURL: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("wikiURL")
    val wikiURL: String
)