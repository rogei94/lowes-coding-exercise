package com.example.lowescodingexercise.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.lowescodingexercise.R
import com.example.lowescodingexercise.databinding.FragmentLookUpBinding
import com.example.lowescodingexercise.model.data.DataState
import com.example.lowescodingexercise.viewmodel.ForecastViewModel
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class LookUpFragment : Fragment() {

    private val forecastViewModel: ForecastViewModel by viewModel()
    private val gson: Gson by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLookUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_look_up, container, false)
        binding.forecastViewModel = forecastViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun initObserver() {
        forecastViewModel.forecast.observe(this, {
            when (it) {
                is DataState.Loading -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    val bundle = bundleOf("forecast" to it.response)
                    view?.findNavController()?.navigate(R.id.go_forecastFragment, bundle)
                }
                is DataState.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}