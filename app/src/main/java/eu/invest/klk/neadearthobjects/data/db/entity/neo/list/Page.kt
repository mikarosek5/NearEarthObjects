package com.example.try_modular.neoResponse

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("number")
    val number: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)