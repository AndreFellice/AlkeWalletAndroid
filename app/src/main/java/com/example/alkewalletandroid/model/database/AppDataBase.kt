package com.example.alkewalletandroid.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alkewalletandroid.model.entities.User
import com.example.alkewalletandroid.model.dao.UserDao

@Database(entities = [User::class], version = 1,exportSchema =  false)
abstract class  AppDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?:synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                 instance
            }
        }
    }
}