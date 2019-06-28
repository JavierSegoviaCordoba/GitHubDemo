package com.javiersc.githubdemo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javiersc.githubdemo.database.entity.User
import com.javiersc.githubdemo.database.entity.UserDetail

@Dao
interface GitHubDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: UserDetail)

    @Query("SELECT * FROM UserDetail WHERE login = :username")
    fun getUserDetail(username: String): LiveData<UserDetail>


}