package com.example.lowescodingexercise.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowescodingexercise.model.data.DataState
import com.example.lowescodingexercise.model.data.Forecast
import com.example.lowescodingexercise.model.repository.abstraction.ForecastRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForecastViewModel(private val forecastRepository: ForecastRepository) : ViewModel() {

    var city: String = ""

    private val _forecast = MutableLiveData<DataState<Forecast>>()
    val forecast: LiveData<DataState<Forecast>> get() = _forecast

    fun onLookUpClicked() {
        viewModelScope.launch {
            forecastRepository.getTheForecast(city).collect {
                _forecast.value = it
            }
        }
    }

}