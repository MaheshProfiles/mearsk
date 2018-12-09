package com.mearsk.weatherreport.presenter

import com.mearsk.weatherreport.BuildConfig
import com.mearsk.weatherreport.api.*
import com.mearsk.weatherreport.http.ConnectionCaller
import com.mearsk.weatherreport.model.WeatherBaseResponse
import com.mearsk.weatherreport.utils.Constants.APP_ID
import com.mearsk.weatherreport.utils.Constants.SUCCESS
import com.mearsk.weatherreport.utils.UrlUtility
import com.mearsk.weatherreport.view.WeatherView

class WeatherViewPresenter constructor(weatherView: WeatherView) : BasePresenter(weatherView) {
    private var mWeatherView: WeatherView? = null
    internal var mConnectionCaller: ConnectionCaller? = null

    init {
        mWeatherView = weatherView
    }

    fun fetchAndLoadData(cityName: String, count: Int) {
        mWeatherView?.showProgressBar()
        val completeUrl: String = UrlUtility.findCity()
        mConnectionCaller = ConnectionCaller(this)
        val baseRequest = BaseRequest()
        baseRequest.weatherAppActionLogger.callStartRequestAtMillis = System.currentTimeMillis()
        baseRequest.requestType = RequestType.GET
        baseRequest.url = completeUrl
        baseRequest.requestId = RequestId.WEATHER_CITY_SEARCH
        baseRequest.addParams("q", cityName)
        baseRequest.addParams("appid", APP_ID)
        baseRequest.addParams("count", "$count")
        mConnectionCaller?.execute(baseRequest, WeatherBaseResponse::class.java)
    }


    fun onDestroy() {
        mConnectionCaller?.cancelActive()
        mConnectionCaller = null
        mWeatherView = null
    }

    override fun onTaskCompleted(responseDetails: BaseResponse) {
        mWeatherView?.hideProgressBar()

        if (responseDetails.weatherBaseResponse?.cod.equals(SUCCESS, true)) {
            responseDetails.weatherBaseResponse?.let { it ->

                when (responseDetails.requestId) {
                    RequestId.WEATHER_CITY_SEARCH -> {
                        mWeatherView?.setWeatherSearchCompleted(it.list)
                    }
                    else -> {
                        mWeatherView?.onFailedApi(Exception())
                    }
                }

            }
        } else {
            val message = responseDetails.weatherBaseResponse?.message
            mWeatherView?.networkFailed(message)
        }
        if (BuildConfig.DEBUG) {
            responseDetails.weatherAppActionLogger.printAllLogs(System.currentTimeMillis())
        }
    }


}