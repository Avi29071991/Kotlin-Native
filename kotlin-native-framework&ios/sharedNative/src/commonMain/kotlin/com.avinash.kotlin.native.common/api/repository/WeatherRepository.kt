package com.avinash.kotlin.native.common.api.repository

import com.avinash.kotlin.native.common.networkModel.DailyData

interface WeatherRepository {

    suspend fun getDailyDataList(latitude: Double, longitude: Double): List<DailyData>?
}