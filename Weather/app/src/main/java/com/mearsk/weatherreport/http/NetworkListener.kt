package com.localqueen.http

import com.mearsk.weatherreport.api.BaseResponse

interface NetworkListener {

    fun onTaskCompleted(responseDetails: BaseResponse)

    fun onFailureResponse(e: Throwable)
}