package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Mission(
    @SerializedName("agencies")
    val agencies: List<Agency>,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("payloads")
    val payloads: List<Any>,
    @SerializedName("type")
    val type: Int,
    @SerializedName("typeName")
    val typeName: String,
    @SerializedName("wikiURL")
    val wikiURL: String
)