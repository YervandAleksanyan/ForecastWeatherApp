package com.weatherapi.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

internal class NetworkLogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val responseCode = response.code
        if (!response.isSuccessful) {
            // network error here from the server side
            // so log the exception to the logger service
            val requestInfo = getRequestInfo(chain)
            val responseBody: ResponseBody? = response.body
            val responseBodyString = responseBody?.string()
            val newResponse = response.newBuilder()
                .body(responseBodyString?.toResponseBody(responseBody.contentType()))
                .build()
            val responseInfo = "Code : $responseCode \n, Response: $responseBodyString"
            response = newResponse
            Log.d("Network","Request Info \n Info: $requestInfo,\n response:$responseInfo")
        }
        return response
    }

    private fun getRequestInfo(chain: Interceptor.Chain): String {
        return buildString {
            val request = chain.request()
            val requestInfoHeaders = String.format(
                "Request %s on %s%n%s",
                request.url,
                chain.connection(),
                request.headers
            )
            val requestBody =
                String.format("REQUEST BODY BEGIN\n%s\nREQUEST BODY END", bodyToString(request))
            append(requestInfoHeaders)
            append("\n")
            append(requestBody)
        }
    }

    private fun bodyToString(request: Request): String? {
        return try {
            val authRequired = request.header(AppRestServiceConstants.NO_AUTH) == null
            if (!authRequired) {
                return ""
            }
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            "did not work"
        }
    }
}
