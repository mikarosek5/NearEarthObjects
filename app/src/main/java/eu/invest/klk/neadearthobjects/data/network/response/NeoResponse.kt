package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName

data class NeoResponse(
    @SerializedName("near_earth_objects")
    val nearEarthObjects: List<NearEarthObject>,
    @SerializedName("page")
    val page: Page
)