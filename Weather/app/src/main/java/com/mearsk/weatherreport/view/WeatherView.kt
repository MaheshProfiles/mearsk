package com.mearsk.weatherreport.view

import com.mearsk.weatherreport.model.WeatherList

interface WeatherView : BaseView {
    fun setWeatherSearchCompleted(weatherList: ArrayList<WeatherList>?)
}