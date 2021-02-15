package com.example.lowescodingexercise.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    @Expose
    @SerializedName("main")
    var title: String,
    @Expose
    @SerializedName("description")
    var description: String
) : Parcelable
