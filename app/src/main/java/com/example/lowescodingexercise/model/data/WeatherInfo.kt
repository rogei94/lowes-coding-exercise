package com.example.lowescodingexercise.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfo(
    @Expose
    @SerializedName("main")
    var temperature: Temperature,
    @Expose
    @SerializedName("weather")
    var weather: List<Weather>
) : Parcelable