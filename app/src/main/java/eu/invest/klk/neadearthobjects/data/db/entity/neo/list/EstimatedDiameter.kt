package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName

data class EstimatedDiameter(
    @SerializedName("kilometers")
    val kilometers: Kilometers,
    @SerializedName("miles")
    val miles: Miles
)