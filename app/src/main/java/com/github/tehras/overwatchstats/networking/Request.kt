package com.github.tehras.overwatchstats.networking

/**
 * Created by koshkin on 7/4/16.
 *
 * Request type
 */
class Request {

    constructor(requestType: Type, url: String, parsingObject: ParsingObject, callbackObj: NetworkResponse) {
        this.requestType = requestType
        this.url = url
        this.parsingObject = parsingObject
        this.networkResponse = callbackObj
    }

    lateinit var requestType: Type
    lateinit var parsingObject: ParsingObject
    lateinit var url: String
    lateinit var requestData: String
    lateinit var networkResponse: NetworkResponse

    enum class Type {
        GET, POST, PUT, DELETE
    }
}

var region: String = "us/"
var system: String = "pc/"
val baseUrl: String = "https://api.lootbox.eu/"
val quickPlay: String = "/quick-play/"
val allHeroes: String = "allHeroes/"
val hero: String = "hero/%s"
val profile: String = "/profile"

val baseOWAPIurl = "https://owapi.net/api/v2/u/"
val generalStatsUrl = "/stats/general"
//https://owapi.net/api/v2/u/SunDwarf-21353/heroes/general
val generalHeroesUrl = "/heroes/general"
//https://owapi.net/api/v2/u/Aurelius-1648/heroes/reinhardt
val generalChampUrl = "/heroes/%s/general"
val competativeChampUrl = "/heroes/%s/competative"


