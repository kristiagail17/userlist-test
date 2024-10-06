package com.example.collabera_test.model.api

import com.example.collabera_test.model.api.model.Results
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val userService: UserService
) : UserService {
    override suspend fun getUsers(results: Int): Results {
        return userService.getUsers(results)
    }
}