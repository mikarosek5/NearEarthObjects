package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Lsp(
    @SerializedName("abbrev")
    val abbrev: String,
    @SerializedName("changed")
    val changed: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("infoURL")
    val infoURL: Any,
    @SerializedName("infoURLs")
    val infoURLs: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("wikiURL")
    val wikiURL: String
)