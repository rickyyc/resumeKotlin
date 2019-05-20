package com.ricky.resumekotlin.app.data

import com.google.gson.annotations.SerializedName

data class Resume(
        @SerializedName("name") val name: String = "",
        @SerializedName("summary") val summary: String = "",
        @SerializedName("experiences") val experiences: List<Experience> = emptyList()
)