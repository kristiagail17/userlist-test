package com.example.collabera_test.model.api.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: JsonElement,
    val state: String,
    val street: Street,
    val timezone: Timezone
)
