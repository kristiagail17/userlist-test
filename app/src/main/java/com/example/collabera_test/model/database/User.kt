package com.example.collabera_test.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity
data class User(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    val name: String = "",
    val gender: String = "",
    val location: String = "",
    val timezone: String = "",
    val email: String = "",
    val dob: String = "",
    val age: String = "",
    val phone: String = "",
    val cell: String = "",
    val id: String = "",
    val picture: String = ""
)