package com.example.data.remote.deserializer

import com.example.data.remote.response.ResponseCurrencyRates
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ResponseCurrenciesDeserializer: JsonDeserializer<ResponseCurrencyRates> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ResponseCurrencyRates {
        val jsonObject: JsonObject = json!!.asJsonObject
        val rates = readParametersMap(jsonObject)
        return jsonObject.run {
            ResponseCurrencyRates(
                base = get("base").asString,
                date = get("date").asString,
                historical = get("historical")?.asBoolean,
                rates = rates,
                success = get("success")?.asBoolean,
                timestamp = get("timestamp").asInt
            )
        }
    }
}

private fun readParametersMap(jsonObject: JsonObject): Map<String, Double> {
    val paramsElement: JsonElement = jsonObject.get("rates")
    val parametersObject: JsonObject  = paramsElement.getAsJsonObject()
    val parameters: MutableMap<String, Double> = mutableMapOf()
    parametersObject.entrySet().forEach {
        parameters[it.key] = it.value.asDouble
    }
    return parameters
}