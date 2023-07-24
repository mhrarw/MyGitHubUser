package com.example.mygithubuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mygithubuser.data.model.ResponseGitHubUser

@Database(entities = [ResponseGitHubUser.Item::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}