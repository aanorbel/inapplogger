package com.velmurugan.inapplogger

import okhttp3.*
import okio.Buffer
import okio.IOException
import org.apache.commons.io.IOUtils


class InAppLoggerInterceptor() : Interceptor {

    private var logger: InAppLogger? = null

    init {
        logger = InAppLogger.loggerInstance
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        val body = response.body
        val bodyString: String? = body?.string()
        val contentType: MediaType? = body?.contentType()
        logger?.api("----------------------------------------")
        logger?.api("Request Url : ${request.url} ")
        logger?.api("Request Method : ${request.method}")
        logger?.api("Request Body : ${bodyToString(request.body)}")
        logger?.api("Response Code : ${response.code}")
        logger?.api("Response : $bodyString")
        logger?.api("----------------------------------------")

        return response.newBuilder().body(ResponseBody.create(contentType, bodyString ?: "")).build();
    }

    fun bodyToString(request: RequestBody?): String? {
        return try {
            val buffer = Buffer()
            if (request != null) request.writeTo(buffer) else return ""
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}