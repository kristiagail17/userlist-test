package com.example.collabera_test.model.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Street(
    val name: String,
    val number: Int
)