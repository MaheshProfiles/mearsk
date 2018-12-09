package com.mearsk.weatherreport.utils

object UrlUtility {
    val HOST = WebServiceUrl.SERVER_URL

    fun findCity(): String {
        return HOST + "data/2.5/find"
    }
}
