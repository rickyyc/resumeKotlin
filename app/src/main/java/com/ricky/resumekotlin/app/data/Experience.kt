package com.ricky.resumekotlin.app.data

import com.google.gson.annotations.SerializedName

data class Experience(
        @SerializedName("company") val company: String,
        @SerializedName("role") val role: String,
        @SerializedName("from") val from: String,
        @SerializedName("to") val to: String,
        @SerializedName("description") val description: String
)