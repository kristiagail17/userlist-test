package com.example.collabera_test.model.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val info: Info,
    val results: List<Result>
)