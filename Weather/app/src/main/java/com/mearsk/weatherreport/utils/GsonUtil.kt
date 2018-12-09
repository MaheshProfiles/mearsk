package com.mearsk.weatherreport.utils

import com.google.gson.Gson

object GsonUtil {

    private val TAG = GsonUtil::class.java.simpleName

    fun <T> fromJson(json: String, classOfT: Class<T>, requestString: String): T? {
        return fromJson(json, true, classOfT, requestString)
    }

    fun <T> fromJson(json: String, logException: Boolean, classOfT: Class<T>, requestString: String): T? {
        var retObj: T?
        val gson = Gson()

        try {
            retObj = gson.fromJson(json, classOfT)
        } catch (e: Exception) {
            if (logException) {
                MRLog.e("$TAG : ERR", e.message)
                MRLog.e("$TAG : REQ", requestString)
                MRLog.e("$TAG : CLA", classOfT.name)
                MRLog.e("$TAG : JSON", json)
            }
            retObj = null
        }
        return retObj
    }

    fun toJson(src: Any?): String? {
        if (src == null) {
            return null
        }

        val gson = Gson()
        var jsonString: String?
        try {
            jsonString = gson.toJson(src)
        } catch (e: Exception) {
            MRLog.e("$TAG : ERR", e.message)
            jsonString = null
        }
        return jsonString
    }
}