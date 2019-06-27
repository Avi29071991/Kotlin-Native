package com.avinash.kotlin.native.common.presentation.presenter

import com.avinash.kotlin.native.common.api.ApiException
import com.avinash.kotlin.native.common.api.repository.WeatherRepository
import com.avinash.kotlin.native.common.presentation.view.WeatherView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherPresenter(private val uiContext: CoroutineContext,
                       private val repository: WeatherRepository) {

    private var view: WeatherView? = null

    fun bind(view: WeatherView) {
        this.view = view
    }

    fun unbind() {
        view = null
    }

    fun loadDailyData(lat: Double, lng: Double) {
        view?.showProgressBar()
        GlobalScope.launch(uiContext) {
            try {
                repository.getDailyDataList(lat, lng)?.let {
                    if (it.isNotEmpty()) {
                        view?.showWeatherData(it)
                    } else {
                        view?.showErrorMessage("No data found")
                    }

                }
            } catch (apiEx: ApiException) {
                view?.showErrorMessage("Api Error: ${apiEx.message}")
            } catch (e: Exception) {
                view?.showErrorMessage(e.message)
            } finally {
                view?.hideProgressBar()
            }
        }
    }
}