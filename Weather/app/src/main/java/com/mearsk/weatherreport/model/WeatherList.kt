package com.mearsk.weatherreport.model

import com.google.gson.annotations.SerializedName

data class WeatherList(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: Coordinates,
    @SerializedName("main") val main: MainWeatherDetails,
    @SerializedName("weather") val weather: ArrayList<WeatherDetails>)