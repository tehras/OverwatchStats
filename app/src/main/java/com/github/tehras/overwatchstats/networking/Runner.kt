package com.github.tehras.overwatchstats.networking

import android.os.AsyncTask
import android.util.Log
import okhttp3.CacheControl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import java.util.*

/**
 * Created by koshkin on 7/4/16.
 *
 * Runner
 */
class Runner : AsyncTask<Request, Void, Response>() {

    init {
        if (runners == null)
            runners = ArrayList()

        runners?.add(this)
    }

    var defaultMediaType = MediaType.parse("application/json; charset=utf-8")

    override fun onPostExecute(result: Response?) {
        if (request != null)
            request!!.networkResponse.onResponse(result!!, request!!)

        runners?.remove(this)
    }

    var request: Request? = null

    override fun doInBackground(vararg request: Request?): Response {
        val req = request[0]
        val response = Response(req!!.parsingObject)
        this.request = req

        try {
            when (req.requestType) {
                Request.Type.GET -> response.httpRequest(req) { get() }
                Request.Type.POST -> response.httpRequest(req) { post(RequestBody.create(defaultMediaType, req.requestData)) }
                Request.Type.DELETE -> response.httpRequest(req) { delete() }
                Request.Type.PUT -> response.httpRequest(req) { put(RequestBody.create(defaultMediaType, req.requestData)) }
            }
        } catch (e: Exception) {
            Log.v(this.javaClass.simpleName, "Exception", e)
            response.status = Response.ResponseStatus.FAILURE
        }

        return response
    }

    private fun Response.httpRequest(request: Request, func: okhttp3.Request.Builder.() -> okhttp3.Request.Builder) {
        val client = getClient()

        Log.d(TAG, "Request url - ${request.url}")

        val builder = defaultBuilder(request)!!.func()
        val okResponse = client.newCall(builder.build()).execute()

        parseResponse(this, okResponse)
    }

    private fun parseResponse(response: Response, okResponse: okhttp3.Response) {
        if (okResponse.isSuccessful) response.status = Response.ResponseStatus.SUCCESS
        else response.status = Response.ResponseStatus.FAILURE

        Log.d(TAG, "Response STATUS - " + response.status)

        if (response.status == Response.ResponseStatus.SUCCESS)
            response.parsingObject.parse(okResponse.body().string())
    }

    val TAG = "Runner"

    private fun defaultBuilder(request: Request): okhttp3.Request.Builder? {
        return okhttp3.Request.Builder()
                .url(request.url).cacheControl(CacheControl.FORCE_NETWORK)
    }

    private fun getClient(): OkHttpClient {
        if (client == null) {
            client = OkHttpClient.Builder()
                    .addNetworkInterceptor {
                        val request = it.request().newBuilder().addHeader("Connection", "close").build()
                        it.proceed(request)
                    }.build()
        }
        return client as OkHttpClient
    }

}

var client: OkHttpClient? = null
var runners: ArrayList<Runner>? = null
