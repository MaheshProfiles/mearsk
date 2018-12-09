package com.mearsk.weatherreport.model

import com.google.gson.annotations.SerializedName

data class Coordinates(@SerializedName("lat") val lat: Double, @SerializedName("lon") val lon: Double)