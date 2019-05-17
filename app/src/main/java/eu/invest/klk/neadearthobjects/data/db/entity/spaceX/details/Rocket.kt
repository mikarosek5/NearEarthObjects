package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("agencies")
    val agencies: List<Agency>,
    @SerializedName("configuration")
    val configuration: String,
    @SerializedName("familyname")
    val familyname: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageSizes")
    val imageSizes: List<Int>,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("infoURL")
    val infoURL: String,
    @SerializedName("infoURLs")
    val infoURLs: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("wikiURL")
    val wikiURL: String
)