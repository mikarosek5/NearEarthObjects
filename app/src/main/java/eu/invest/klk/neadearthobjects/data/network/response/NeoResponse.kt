package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.Page

data class NeoResponse(
    @SerializedName("near_earth_objects")
    val nearEarthObjects: List<NearEarthObject>,
    @SerializedName("page")
    val page: Page
)