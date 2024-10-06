package com.example.collabera_test.model.repository

import com.example.collabera_test.model.api.UserService
import com.example.collabera_test.model.database.User
import com.example.collabera_test.model.database.UsersDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService, private val usersDao: UsersDao): UserRepository {
    override suspend fun fetchAndSaveUsers(size: Int): List<User> {
        val results = userService.getUsers(size)

        val users = results.results.map {
            it.toUser()
        }

        usersDao.insertUsers(users)

        return users
    }

    override suspend fun getUserById(uuid: String): User? {
        return usersDao.getUserById(uuid = uuid)
    }
}