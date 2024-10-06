package com.example.collabera_test.model.api.model

import com.example.collabera_test.model.database.User
import com.example.collabera_test.util.DateConverter
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Result(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
) {
    fun toUser(): User {
        return User(
            name = "${this.name.title} ${this.name.first} ${this.name.last}",
            gender = this.gender,
            location = "${this.location.street.number} ${this.location.street.name}, ${this.location.city}, ${this.location.state}, ${this.location.country} ${this.location.postcode}",
            timezone = "(${this.location.timezone.offset} ${this.location.timezone.description})",
            email = this.email,
            dob = DateConverter.getDOB(this.dob.date),
            age = this.dob.age.toString(),
            phone = this.phone,
            cell = this.cell,
            picture = this.picture.large,
            id = (if (this.id.value.isNullOrEmpty()) "No ID" else "${this.id.name} - ${this.id.value}" )
        )
    }
}

