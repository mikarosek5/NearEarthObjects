package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName

data class OrbitClass(
    @SerializedName("orbit_class_description")
    val orbitClassDescription: String,
    @SerializedName("orbit_class_range")
    val orbitClassRange: String,
    @SerializedName("orbit_class_type")
    val orbitClassType: String
)