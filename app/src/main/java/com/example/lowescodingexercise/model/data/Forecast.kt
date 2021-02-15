package com.example.lowescodingexercise.model.data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    @Expose
    @SerializedName("city")
    var city: City,
    @Expose
    @SerializedName("list")
    var weatherList: List<WeatherInfo>
) : Parcelable