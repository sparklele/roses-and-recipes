package com.example.roses_and_recipes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roses_and_recipes.data.model.favourite
import com.example.roses_and_recipes.data.model.meal

@Dao
interface FavDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<favourite>

    @Query("SELECT * FROM recipes WHERE id = :rId")
    suspend fun getRecipeById(rId: Int): meal

    @Insert
    suspend fun insertFav(fav: favourite)

    @Delete
    suspend fun deleteFav(fav: favourite)

    @Update
    suspend fun updateFav(fav: favourite)

}