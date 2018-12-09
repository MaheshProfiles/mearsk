package com.mearsk.weatherreport.http

import com.mearsk.weatherreport.BuildConfig
import com.mearsk.weatherreport.utils.MRLog

class WeatherAppActionLogger {

    private val TAG = "Total TIME : "

    // on start of making api call
    var callStartRequestAtMillis: Long = 0

    // on Task complete action call
    var receivedParsedResponseAtMillis: Long = 0

    // after setting up on UI
    var setUIParsedResponseAtMillis: Long = 0

    // These attribute for api time tracking
    var sentRequestAtMillis: Long = 0
    var receivedResponseAtMillis: Long = 0

    // Tag for track particular request call
    var requestTag: String? = ""

    fun logApiRequestAndResponseTimeDiff() {
        MRLog.d("$TAG API call:: $requestTag :", "${receivedResponseAtMillis - sentRequestAtMillis}")
    }

    fun logParsingDataTimeDiff() {
        MRLog.d("$TAG Parsed data::", "${receivedParsedResponseAtMillis - receivedResponseAtMillis}")
    }

    fun logCallApiRequestAndReceiveParseDataTimeDiff() {
        MRLog.d(
            "$TAG from called state to - Parsed state::",
            "${receivedParsedResponseAtMillis - callStartRequestAtMillis}"
        )
    }

    fun logCallApiRequestAndUIRenderTimeDiff() {
        MRLog.d(
            "$TAG from called state to - Render state::",
            "${setUIParsedResponseAtMillis - callStartRequestAtMillis}"
        )
    }

    fun printAllLogs(currentTimeMillis: Long) {
        if (BuildConfig.DEBUG) {
            setUIParsedResponseAtMillis = currentTimeMillis
            logApiRequestAndResponseTimeDiff()
            logParsingDataTimeDiff()
            logCallApiRequestAndReceiveParseDataTimeDiff()
            logCallApiRequestAndUIRenderTimeDiff()
        }
    }
}