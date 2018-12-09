package com.mearsk.weatherreport.api

import com.mearsk.weatherreport.http.WeatherAppActionLogger
import com.mearsk.weatherreport.model.WeatherBaseResponse

class BaseResponse {
    var requestId: Int = RequestId.NONE
    var weatherBaseResponse: WeatherBaseResponse? = null
    var responseJson: String = ""
    var httpRequest: okhttp3.Request? = null
    var weatherAppActionLogger: WeatherAppActionLogger = WeatherAppActionLogger()
    var status = -1
    var completeResponse: okhttp3.Response? = null
}