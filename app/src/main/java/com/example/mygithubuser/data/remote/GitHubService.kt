package com.example.mygithubuser.data.remote

import com.example.mygithubuser.BuildConfig
import com.example.mygithubuser.data.model.ResponseDetailUser
import com.example.mygithubuser.data.model.ResponseGitHubUser
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface GitHubService {
    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(@Header("Authorization") authorization: String = BuildConfig.KEY): MutableList<ResponseGitHubUser.Item>

    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username")username : String, @Header("Authorization") authorization: String = BuildConfig.KEY): ResponseDetailUser

    @JvmSuppressWildcards
    @GET("users/{username}/followers")
    suspend fun getFollowersUser(@Path("username")username : String, @Header("Authorization") authorization: String = BuildConfig.KEY): MutableList<ResponseGitHubUser.Item>

    @JvmSuppressWildcards
    @GET("users/{username}/following")
    suspend fun getFollowingUser(@Path("username")username : String,@Header("Authorization") authorization: String = BuildConfig.KEY): MutableList<ResponseGitHubUser.Item>

    @JvmSuppressWildcards
    @GET("search/users")
    suspend fun searchUserGitHub(@QueryMap params: Map<String, Any>, @Header("Authorization") authorization: String = BuildConfig.KEY): ResponseGitHubUser
}