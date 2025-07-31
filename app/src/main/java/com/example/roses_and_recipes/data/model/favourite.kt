package com.example.roses_and_recipes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class favourite(
    @PrimaryKey val user: user,
    val meal: meal
)
