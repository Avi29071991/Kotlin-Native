package com.avinash.kotlin.native.common.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class DailyData(
    var time: Long?,
    var summary: String?,
    var icon: String?,
    var sunriseTime: Int?,
    var sunsetTime: Int?,
    var temperatureMin: Double?,
    var temperatureMinTime: Int?,
    var temperatureMax: Double?,
    var temperatureMaxTime: Int?,
    var apparentTemperatureMin: Double?,
    var apparentTemperatureMinTime: Int?,
    var apparentTemperatureMax: Double?,
    var apparentTemperatureMaxTime: Int?,
    var dewPoint: Double?,
    var humidity: Double?,
    var windSpeed: Double?,
    var windBearing: Int?,
    var visibility: Double?,
    var cloudCover: Double?,
    var pressure: Double?,
    var ozone: Double?
)