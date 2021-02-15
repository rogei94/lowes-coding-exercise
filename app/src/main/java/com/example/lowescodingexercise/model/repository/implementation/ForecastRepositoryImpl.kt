package com.example.lowescodingexercise.model.repository.implementation

import com.example.lowescodingexercise.model.api.ForecastService
import com.example.lowescodingexercise.model.data.DataState
import com.example.lowescodingexercise.model.data.Forecast
import com.example.lowescodingexercise.model.repository.abstraction.ForecastRepository
import com.example.lowescodingexercise.util.API_KEY
import com.example.lowescodingexercise.util.UNITS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ForecastRepositoryImpl(private val forecastService: ForecastService) : ForecastRepository {

    override fun getTheForecast(city: String): Flow<DataState<Forecast>> = flow {
        emit(DataState.Loading("Getting forecast report..."))
        emit(
            DataState.Success(
                forecastService.getForecast(
                    city, UNITS, API_KEY
                )
            )
        )
    }.flowOn(Dispatchers.IO).catch {
        emit(DataState.Error(it.message!!))
    }

}