package com.github.tehras.overwatchstats.networking

/**
 * Created by koshkin on 7/4/16.
 */
interface NetworkResponse {

    fun onResponse(response: Response, request: Request)

    fun onDbResponse(obj: Any?) {
        //by default nothing
    }

}
