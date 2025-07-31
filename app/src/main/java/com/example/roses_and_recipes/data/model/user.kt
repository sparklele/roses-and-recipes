package com.example.roses_and_recipes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class user(
    @PrimaryKey val username: String,
    val password: String
)
