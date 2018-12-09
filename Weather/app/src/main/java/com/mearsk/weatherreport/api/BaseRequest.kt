package com.mearsk.weatherreport.api

import com.mearsk.weatherreport.http.WeatherAppActionLogger
import com.mearsk.weatherreport.utils.UrlUtility

class BaseRequest {
    var requestId: Int = RequestId.NONE
    var shareAction: String? = ""
    var url: String = ""
    var header: HashMap<String, String>? = null
    var requestBody: String = ""
    var isForcedNetworkCall: Boolean = false
    @RequestType.Interface
    var requestType: Int = RequestType.GET
    var param: HashMap<String, String>? = null
    val weatherAppActionLogger: WeatherAppActionLogger = WeatherAppActionLogger()

    fun addParams(key: String, value: String) {
        if (param == null) {
            param = HashMap()
        }
        param?.put(key, value)
    }

    fun addAllParams(params: HashMap<String, String>) {
        if (param == null) {
            param = HashMap()
        }
        param?.putAll(params)
    }

    fun addHeader(key: String, value: String) {
        if (header == null) {
            header = HashMap()
        }
        header?.put(key, value)
    }

    fun addAllHeader(headers: HashMap<String, String>) {
        if (header == null) {
            header = HashMap()
        }
        header?.putAll(headers)
    }

    fun getRequestTag(): String {
        return url.replace(UrlUtility.HOST, "")
    }
}