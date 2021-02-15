package com.example.lowescodingexercise.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("country")
    var country: String
) : Parcelable
