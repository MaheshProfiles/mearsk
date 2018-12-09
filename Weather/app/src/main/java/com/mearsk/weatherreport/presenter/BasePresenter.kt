package com.mearsk.weatherreport.presenter

import com.localqueen.http.NetworkListener
import com.mearsk.weatherreport.view.BaseView

abstract class BasePresenter(private val baseView: BaseView) : NetworkListener {

    final override fun onFailureResponse(e: Throwable) {
        baseView.onFailedApi(e)
    }
}