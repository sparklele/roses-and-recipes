package com.example.roses_and_recipes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class meal(
    @PrimaryKey(autoGenerate = true) val id : Int
)
