package com.example.lowescodingexercise.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowescodingexercise.R
import com.example.lowescodingexercise.databinding.FragmentForecastBinding
import com.example.lowescodingexercise.model.data.Forecast
import com.example.lowescodingexercise.model.data.WeatherInfo
import com.example.lowescodingexercise.view.adapter.ForecastAdapter
import com.example.lowescodingexercise.view.adapter.callback.OnItemClicked


class ForecastFragment : Fragment(), OnItemClicked {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private var city: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
        val forecast = arguments?.getParcelable<Forecast>("forecast")
        val weatherInfo = forecast?.weatherList
        if (forecast != null) {
            city = forecast.city.name
            binding.WeatherToolbar.toolbar.title = city
        }
        if (weatherInfo != null)
            initRecyclerView(weatherInfo)
    }

    private fun initRecyclerView(weatherInfo: List<WeatherInfo>) {
        val layoutManage = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            context,
            layoutManage.orientation
        )
        binding.forecastRecycler.apply {
            layoutManager = layoutManage
            adapter = ForecastAdapter(weatherInfo, this@ForecastFragment)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onItemWeatherClicked(weatherInfo: WeatherInfo) {
        val bundle = bundleOf("weather_info" to weatherInfo, "city" to city)
        view?.findNavController()?.navigate(R.id.go_weatherDetailFragment, bundle)
    }

}