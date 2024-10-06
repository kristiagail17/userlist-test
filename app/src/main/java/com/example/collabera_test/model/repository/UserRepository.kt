package com.example.collabera_test.model.repository

import com.example.collabera_test.model.database.User

interface UserRepository {
    suspend fun fetchAndSaveUsers(size: Int): List<User>
    suspend fun getUserById(uuid: String): User?
}