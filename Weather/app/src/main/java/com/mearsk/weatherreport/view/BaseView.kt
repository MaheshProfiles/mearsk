package com.mearsk.weatherreport.view

interface BaseView {

    fun showProgressBar()

    fun hideProgressBar()

    fun onFailedApi(e: Throwable)

    fun networkFailed(message: String?)

}