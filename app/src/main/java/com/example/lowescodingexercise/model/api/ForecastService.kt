package com.example.lowescodingexercise.model.api

import com.example.lowescodingexercise.model.data.Forecast
import retrofit2.http.GET
import retrofit2.http.Query


interface ForecastService {

    @GET("forecast?")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Forecast

}