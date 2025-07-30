package com.example.roses_and_recipes.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roses_and_recipes.data.local.dao.RecipeDao
import com.example.roses_and_recipes.data.local.dao.UserDao
import com.example.roses_and_recipes.data.model.meal
import com.example.roses_and_recipes.data.model.user

@Database(entities = [meal::class, user::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun recipeDao() : RecipeDao
    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "recipes_database"
            )
                .fallbackToDestructiveMigration(false)
                .build()
                .also { INSTANCE = it }
        }
    }

}
