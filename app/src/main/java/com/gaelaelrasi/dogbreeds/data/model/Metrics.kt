package com.gaelaelrasi.dogbreeds.data.model

import com.google.gson.annotations.SerializedName

data class Metrics (

    @SerializedName("imperial")
    var imperial: String? = null,

    @SerializedName("metric")
    var metric: String? = null
)
