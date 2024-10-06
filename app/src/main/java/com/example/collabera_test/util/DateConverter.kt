package com.example.collabera_test.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateConverter {
    fun getDOB(dateString: String): String {

        val outputFormat = DateTimeFormatter.ofPattern("MMMM dd yyyy")

        try {
            val instant = Instant.parse(dateString)
            val zonedDateTime = instant.atZone(ZoneId.of("UTC"))
            return(zonedDateTime.format(outputFormat))

        } catch (e: DateTimeParseException) {
            println("Invalid date format: ${e.message}")
            return "N/A"
        }
    }
}