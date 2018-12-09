package com.mearsk.weatherreport.http

import com.localqueen.http.NetworkListener
import com.mearsk.weatherreport.BuildConfig
import com.mearsk.weatherreport.api.BaseRequest
import com.mearsk.weatherreport.api.BaseResponse
import com.mearsk.weatherreport.model.WeatherBaseResponse
import com.mearsk.weatherreport.utils.GsonUtil
import com.mearsk.weatherreport.utils.MRLog
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException


/* *
 * This class is having Integration of
 * RX-Java & OkHttp calling
 */
class ConnectionCaller(private val mNetworkListener: NetworkListener) {
    private var call: Call? = null

    fun <T : WeatherBaseResponse> execute(baseRequest: BaseRequest, classOfT: Class<T>) {

        Observable.just(baseRequest)
            .map { request ->
                val baseResponse = BaseResponse()
                val client = OkHttpClient()
                val httpRequest = NetworkApiRequest.getApiRequest(request)
                MRLog.d("$TAG : url", httpRequest.url().toString())
                call = client.newCall(httpRequest)
                try {
                    val httpResponse = call!!.execute()
                    val status = httpResponse.code()
                    val responseJson = httpResponse.body()!!.string()
                    MRLog.d("$TAG : status", status.toString() + "")
                    baseResponse.status = status

                    if (BuildConfig.DEBUG) {
                        baseResponse.responseJson = responseJson
                        baseResponse.httpRequest = httpRequest
                        baseResponse.completeResponse = httpResponse
                    }

                    baseResponse.requestId = request.requestId

                    val responseDetails = GsonUtil.fromJson(responseJson, classOfT, request.url)
                    baseResponse.weatherBaseResponse = responseDetails

                    if (BuildConfig.DEBUG) {
                        baseResponse.weatherAppActionLogger = request.weatherAppActionLogger
                        baseResponse.weatherAppActionLogger.requestTag = request.getRequestTag()
                        baseResponse.weatherAppActionLogger.receivedParsedResponseAtMillis = System.currentTimeMillis()
                        baseResponse.weatherAppActionLogger.sentRequestAtMillis = httpResponse.sentRequestAtMillis()
                        baseResponse.weatherAppActionLogger.receivedResponseAtMillis =
                                httpResponse.receivedResponseAtMillis()
                    }

                } catch (e: IOException) {
                    MRLog.e("$TAG : ERR", e.message)
                }

                baseResponse
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseResponse> {
                override fun onSubscribe(d: Disposable) {
                    MRLog.d("$TAG : Observer", "onSubscribe")
                }

                override fun onNext(responseDetails: BaseResponse) {
                    mNetworkListener.onTaskCompleted(responseDetails)
                    MRLog.d("$TAG : Observer", "onNext")
                }

                override fun onError(e: Throwable) {
                    MRLog.d("$TAG : Observer", "onError")
                    mNetworkListener.onFailureResponse(e)
                }

                override fun onComplete() {
                    MRLog.d("$TAG : Observer", "onComplete")
                }
            })
    }

    fun cancelActive() {
        if (call != null) {
            call!!.cancel()
        }
    }

    companion object {
        private val TAG = ConnectionCaller::class.java.simpleName
    }
}