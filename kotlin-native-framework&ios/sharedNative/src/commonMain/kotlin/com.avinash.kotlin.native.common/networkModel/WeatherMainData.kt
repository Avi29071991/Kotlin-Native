package com.avinash.kotlin.native.common.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class WeatherMainData (
    var daily: DailyMain? = null
)