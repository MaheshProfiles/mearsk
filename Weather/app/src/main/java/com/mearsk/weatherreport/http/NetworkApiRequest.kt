package com.mearsk.weatherreport.http

import com.mearsk.weatherreport.api.BaseRequest
import com.mearsk.weatherreport.api.RequestType
import okhttp3.CacheControl
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

/**
 * This Class is having the control for Cache Enable and Disable
 */
class NetworkApiRequest {

    companion object {

        fun getApiRequest(baseRequest: BaseRequest): Request {
            return if (baseRequest.isForcedNetworkCall) {
                forceNetworkResponse(baseRequest)
            } else {
                forceCacheResponse(baseRequest)
            }
        }

        private fun forceNetworkResponse(baseRequest: BaseRequest): Request {
            val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseRequest.requestBody)
            var builder: Request.Builder = Request.Builder()
                    .cacheControl(CacheControl.Builder()
                            .maxAge(0, TimeUnit.SECONDS)
                            .build())
                    .url(setQueryParameterIfEligible(baseRequest))

            setRequestType(baseRequest, builder, body)

            baseRequest.header?.let {
                it.forEach { (key, value) ->
                    builder.addHeader(key, value)
                }
            }
            return builder.build()
        }

        private fun forceCacheResponse(baseRequest: BaseRequest): Request {
            val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), baseRequest.requestBody)
            var builder: Request.Builder = Request.Builder()
                    .cacheControl(CacheControl.Builder()
                            .maxStale(30, TimeUnit.MINUTES)
                            .build())
                    .url(setQueryParameterIfEligible(baseRequest))

            setRequestType(baseRequest, builder, body)

            baseRequest.header?.let {
                it.forEach { (key, value) ->
                    builder.addHeader(key, value)
                }
            }
            return builder.build()
        }

        private fun setQueryParameterIfEligible(baseRequest: BaseRequest): String {

            var url = baseRequest.url
            if (!url.contains('?')) {
                url += '?'
            }
            baseRequest.param?.let {
                it.forEach { (key, value) ->
                    url += "$key=$value&"
                }
            }

            return url
        }

        private fun setRequestType(baseRequest: BaseRequest, builder: Request.Builder, body: RequestBody?) {
            when (baseRequest.requestType) {
                RequestType.GET -> builder.get()
                RequestType.POST -> builder.post(body)
                RequestType.DELETE -> {
                    if (body == null) {
                        builder.delete()
                    } else {
                        builder.delete(body)
                    }
                }
                RequestType.PUT -> builder.put(body)
                RequestType.PATCH -> builder.patch(body)
            }
        }
    }
}