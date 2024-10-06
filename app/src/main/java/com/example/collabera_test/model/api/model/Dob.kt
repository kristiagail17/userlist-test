package com.example.collabera_test.model.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Dob(
    val age: Int,
    val date: String
)