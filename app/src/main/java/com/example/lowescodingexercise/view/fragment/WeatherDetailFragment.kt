package com.example.lowescodingexercise.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.lowescodingexercise.R
import com.example.lowescodingexercise.databinding.FragmentLookUpBinding
import com.example.lowescodingexercise.databinding.FragmentWeatherDetailBinding
import com.example.lowescodingexercise.model.data.Forecast
import com.example.lowescodingexercise.model.data.WeatherInfo
import java.text.NumberFormat


class WeatherDetailFragment : Fragment() {

    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
        val weatherInfo = arguments?.getParcelable<WeatherInfo>("weather_info")
        val city = arguments?.getString("city")
        if (city != null)
            binding.weatherDetailToolbar.toolbar.title = city
        if (weatherInfo != null)
            setWeatherInfo(weatherInfo)
    }

    private fun setWeatherInfo(weatherInfo: WeatherInfo) {
        binding.textTemperature.text =
            NumberFormat.getIntegerInstance().format(weatherInfo.temperature.temperature)
        binding.textFeelslike.text =
            NumberFormat.getIntegerInstance().format(weatherInfo.temperature.feelsLike)
        binding.textWeatherTitle.text = weatherInfo.weather[0].title
        binding.textWeatherDesc.text = weatherInfo.weather[0].description
    }

}