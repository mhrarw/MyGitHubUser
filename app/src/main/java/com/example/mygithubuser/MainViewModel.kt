package com.example.mygithubuser

import android.util.Log
import androidx.lifecycle.*
import com.example.mygithubuser.data.local.SettingPreferences
import com.example.mygithubuser.data.remote.ApiClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val preferences: SettingPreferences) : ViewModel() {

    val resultUser = MutableLiveData<com.example.mygithubuser.utils.Result>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun getUser() {
        viewModelScope.launch {
                flow {
                    val response = ApiClient
                        .githubService
                        .getUserGithub()
                    emit(response)
                }.onStart {
                    resultUser.value = com.example.mygithubuser.utils.Result.Loading(true)
                }.onCompletion {
                    resultUser.value = com.example.mygithubuser.utils.Result.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultUser.value = com.example.mygithubuser.utils.Result.Error(it)
                }.collect {
                    resultUser.value = com.example.mygithubuser.utils.Result.Success(it)
                }
        }
    }

    fun getUser(username: String) {
        viewModelScope.launch {
                flow {
                    val response = ApiClient
                        .githubService
                        .searchUserGitHub(
                            mapOf(
                                "q" to username,
                                "per_page" to 10
                            )
                        )

                    emit(response)
                }.onStart {
                    resultUser.value = com.example.mygithubuser.utils.Result.Loading(true)
                }.onCompletion {
                    resultUser.value = com.example.mygithubuser.utils.Result.Loading(false)
                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultUser.value = com.example.mygithubuser.utils.Result.Error(it)
                }.collect {
                    resultUser.value = com.example.mygithubuser.utils.Result.Success(it.items)
                }
        }
    }

    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}