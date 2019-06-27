package com.avinash.kotlin.native.common.networkModel

import kotlinx.serialization.Serializable

@Serializable
data class DailyMain (
    var summary: String? = null,
    var icon: String? = null,
    var data: List<DailyData> = ArrayList()
)