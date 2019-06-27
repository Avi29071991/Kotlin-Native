package com.avinash.kotlin.native.common.api.repository

import com.avinash.kotlin.native.common.api.WeatherApi
import com.avinash.kotlin.native.common.networkModel.DailyData

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {

    override suspend fun getDailyDataList(latitude: Double, longitude: Double): List<DailyData>? {
        return api.getDailyDataList(latitude, longitude)
    }
}