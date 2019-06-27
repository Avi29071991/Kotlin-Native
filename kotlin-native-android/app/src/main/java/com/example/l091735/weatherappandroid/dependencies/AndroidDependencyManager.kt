package com.example.l091735.weatherappandroid.dependencies

import com.avinash.kotlin.native.common.WeatherDataConverter
import com.avinash.kotlin.native.common.api.WeatherApi
import com.avinash.kotlin.native.common.api.repository.WeatherRepository
import com.avinash.kotlin.native.common.api.repository.WeatherRepositoryImpl
import com.avinash.kotlin.native.common.presentation.presenter.WeatherPresenter
import kotlinx.coroutines.Dispatchers

class AndroidDependencyManager {

    private val repository: WeatherRepository by lazy {
        WeatherRepositoryImpl(WeatherApi())
    }

    private val uiContext by lazy { Dispatchers.Main }

    val weatherPresenter: WeatherPresenter by lazy {
        WeatherPresenter(uiContext,repository)
    }

    val dataConverter: WeatherDataConverter by lazy {
        WeatherDataConverter()
    }
}

fun dependencies(): AndroidDependencyManager {
    return AndroidDependencyManager()
}