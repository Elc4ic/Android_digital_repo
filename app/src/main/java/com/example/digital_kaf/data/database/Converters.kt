package com.example.digital_kaf.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

@ProvidedTypeConverter
class Converters private constructor(private val json: Json) {
    @TypeConverter
    fun fromString(value: String): List<Double> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<Double>): String {
        return json.encodeToString(list)
    }

    companion object {
        fun create(json: Json): Converters {
            return Converters(json)
        }
    }
}