package com.avinash.kotlin.native.common.api

import com.avinash.kotlin.native.common.networkModel.DailyData
import com.avinash.kotlin.native.common.networkModel.WeatherMainData
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.response.readText
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

/**
 * Class used to make service calls to the required API
 */
class WeatherApi {

    /**
     * Method used to call weather endpoint to fetch data from server
     * @param latitude Location's latitude
     * @param longitude Location's longitude
     *
     * @return List of daily weather data from the server
     */
    suspend fun getDailyDataList(latitude: Double, longitude: Double): List<DailyData>? {
        val urlString = "https://api.forecast.io/forecast/137e3e8eea26c1710682e310b86b0b56/$latitude,$longitude"

        val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = JsonKotlinSerializer().apply {
                    setMapper(WeatherMainData.serializer())
                }
            }
            install(ExpectSuccess)
        }

        val response = httpClient.call(urlString) {
            method = HttpMethod.Get
        }.response.readText()

        val data: WeatherMainData =  Json.nonstrict.parse(WeatherMainData.serializer(), response)
        return data.daily?.data
    }
}