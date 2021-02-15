package com.example.lowescodingexercise.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lowescodingexercise.R
import com.example.lowescodingexercise.model.data.WeatherInfo
import com.example.lowescodingexercise.view.adapter.callback.OnItemClicked
import java.text.NumberFormat

class ForecastAdapter(
    private val weatherInfo: List<WeatherInfo>,
    private val callbackWeather: OnItemClicked
) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textWeather: TextView = itemView.findViewById(R.id.textWeather)
        val descTemperature: TextView = itemView.findViewById(R.id.descTemperature)
        val itemWeather: ConstraintLayout = itemView.findViewById(R.id.itemWeather)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false) as View
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val weather = weatherInfo[position]
        holder.textWeather.text = weather.weather[0].title
        holder.descTemperature.text =
            NumberFormat.getIntegerInstance().format(weather.temperature.temperature)
        holder.itemWeather.setOnClickListener {
            callbackWeather.onItemWeatherClicked(weather)
        }
    }

    override fun getItemCount(): Int = weatherInfo.size
}