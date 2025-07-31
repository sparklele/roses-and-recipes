package com.example.roses_and_recipes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roses_and_recipes.data.model.user

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUser(username: String): user?

    @Insert
    suspend fun insertUser(user: user)
}