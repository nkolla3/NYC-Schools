package com.nyc.schools.model

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("school_name") val school_name: String,
    @SerializedName("phone_number") val phone_number: String,
    @SerializedName("city") val city: String,
    @SerializedName("dbn") val dbn: String,
)