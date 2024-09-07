package com.sagmade.vinoapp

import retrofit2.http.GET

interface WineService {
    @GET(Constant.PATH_WINE)
    suspend fun getRedsWines() : List<Wine>
}