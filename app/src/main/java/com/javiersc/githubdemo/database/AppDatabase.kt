package com.javiersc.githubdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.javiersc.githubdemo.database.entity.User
import com.javiersc.githubdemo.database.entity.UserDetail

@Database(entities = [User::class, UserDetail::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "GitHub").fallbackToDestructiveMigration().build()
    }

    abstract val gitHubDAO: GitHubDAO

}