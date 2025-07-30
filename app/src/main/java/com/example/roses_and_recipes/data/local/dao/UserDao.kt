package com.example.roses_and_recipes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.roses_and_recipes.data.model.user

@Dao
interface UserDao {
    @Query("SELECT 1 FROM users WHERE username = :username")
    suspend fun getUser(username: String): user?
}