package com.avinash.kotlin.native.common

import kotlin.math.round

class WeatherDataConverter {

    companion object {
        const val RAIN = "rain"
        const val HAIL = "hail"
        const val THUNDERSTORM = "thunderstorm"
        const val CLEAR_DAY = "clear-day"
        const val CLEAR_NIGHT = "clear-night"
        const val SNOW = "snow"
        const val WIND = "wind"
        const val SLEET = "sleet"

        const val CLOUDY = "cloudy"
        const val FOG = "fog"
        const val PARTLY_CLOUDY_NIGHT = "partly-cloudy-night"
        const val PARTLY_CLOUDY_DAY = "partly-cloudy-day"
    }

    /**
     * Method used to get the image name required for displaying it on respective devices
     *
     * @param type type of image for particular weather condition
     * @return name of the image
     */
    fun getIcon(type: String?): String {
        return type?.let {
            when (it) {
                SNOW, WIND, SLEET -> SNOW
                CLOUDY, PARTLY_CLOUDY_DAY, PARTLY_CLOUDY_NIGHT -> CLOUDY
                else -> it
            }
        } ?: run { "dunno" }
    }

    /**
     * Method used to convert fahrenheit temperature data to celcius
     * @param fahrenheit temperature data in fahrenheit
     *
     * @return Temperature data in celcius
     */
    fun getCelcius(fahrenheit: Double): Double {
        return round(((5 / 9.0) * (fahrenheit - 32)) * 100 / 100)
    }
}