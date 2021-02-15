package com.example.lowescodingexercise.view.adapter.callback

import com.example.lowescodingexercise.model.data.WeatherInfo

interface OnItemClicked {
    fun onItemWeatherClicked(weatherInfo: WeatherInfo)
}