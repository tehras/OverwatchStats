package com.github.tehras.overwatchstats.networking

import com.github.tehras.overwatchstats.networking.ParsingObject

/**
 * Created by koshkin on 7/4/16.
 */
class Response {

    constructor(o: ParsingObject) {
        parsingObject = o
    }

    lateinit var parsingObject: ParsingObject
    var status: ResponseStatus = ResponseStatus.STARTED

    enum class ResponseStatus {
        SUCCESS, FAILURE, STARTED
    }
}