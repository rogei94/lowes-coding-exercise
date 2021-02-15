package com.example.lowescodingexercise.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    @Expose
    @SerializedName("temp")
    var temperature: Double,
    @Expose
    @SerializedName("feels_like")
    var feelsLike: Double
) : Parcelable
