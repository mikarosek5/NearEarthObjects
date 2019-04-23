package eu.invest.klk.neadearthobjects.data.db.entity.neo.list

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("number")
    val number: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)