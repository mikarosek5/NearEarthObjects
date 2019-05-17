package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("infoURL")
    val infoURL: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pads")
    val pads: List<Pad>,
    @SerializedName("wikiURL")
    val wikiURL: String
)