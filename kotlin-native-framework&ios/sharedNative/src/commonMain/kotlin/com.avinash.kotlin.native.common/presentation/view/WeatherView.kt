package com.avinash.kotlin.native.common.presentation.view

import com.avinash.kotlin.native.common.networkModel.DailyData
import com.avinash.kotlin.native.common.presentation.BaseView

interface WeatherView : BaseView {

    fun showWeatherData(dataList: List<DailyData>)
}