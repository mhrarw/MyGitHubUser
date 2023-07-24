package com.example.mygithubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mygithubuser.data.model.ResponseGitHubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ResponseGitHubUser.Item)

    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<MutableList<ResponseGitHubUser.Item>>

    @Query("SELECT * FROM User WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): ResponseGitHubUser.Item

    @Delete
    fun delete(user: ResponseGitHubUser.Item)
}