package com.example.lowescodingexercise.model.repository.abstraction

import com.example.lowescodingexercise.model.data.DataState
import com.example.lowescodingexercise.model.data.Forecast
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    fun getTheForecast(city: String): Flow<DataState<Forecast>>

}