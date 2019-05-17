package eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details


import com.google.gson.annotations.SerializedName

data class Launche(
    @SerializedName("changed")
    val changed: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isoend")
    val isoend: String,
    @SerializedName("isonet")
    val isonet: String,
    @SerializedName("isostart")
    val isostart: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("lsp")
    val lsp: Lsp,
    @SerializedName("missions")
    val missions: List<Mission>,
    @SerializedName("name")
    val name: String,
    @SerializedName("net")
    val net: String,
    @SerializedName("netstamp")
    val netstamp: Int,
    @SerializedName("probability")
    val probability: Int,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tbddate")
    val tbddate: Int,
    @SerializedName("tbdtime")
    val tbdtime: Int,
    @SerializedName("vidURL")
    val vidURL: Any,
    @SerializedName("vidURLs")
    val vidURLs: List<String>,
    @SerializedName("westamp")
    val westamp: Int,
    @SerializedName("windowend")
    val windowend: String,
    @SerializedName("windowstart")
    val windowstart: String,
    @SerializedName("wsstamp")
    val wsstamp: Int
)