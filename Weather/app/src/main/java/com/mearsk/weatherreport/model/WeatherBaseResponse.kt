package com.mearsk.weatherreport.model

import com.google.gson.annotations.SerializedName

data class WeatherBaseResponse(
    @SerializedName("message") val message: String,
    @SerializedName("cod") val cod: String,
    @SerializedName("count") val count: String,
    @SerializedName("list") val list: ArrayList<WeatherList>
)