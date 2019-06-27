package com.avinash.kotlin.native.common.api

import io.ktor.client.call.TypeInfo
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

class JsonKotlinSerializer : JsonSerializer {
    val mappers = mutableMapOf<KClass<Any>, KSerializer<Any>>()

    inline fun <reified T> setMapper(serializer: KSerializer<T>) {
        mappers[T::class as KClass<Any>] = serializer as KSerializer<Any>
    }

    override fun write(data: Any): OutgoingContent {
        @Suppress("UNCHECKED_CAST")
        val content = Json.nonstrict.stringify(mappers[data::class]!!, data)
        return TextContent(content, ContentType.Application.Json)
    }

    override suspend fun read(type: TypeInfo, response: HttpResponse): Any {
        val mapper = mappers[type.type]!!
        val text = response.readText()

        return Json.nonstrict.parse(mapper, text)
    }
}