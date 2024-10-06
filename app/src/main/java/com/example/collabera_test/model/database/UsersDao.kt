package com.example.collabera_test.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Query("SELECT * from user WHERE uuid = :uuid")
    suspend fun getUserById(uuid: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(items: List<User>)
}