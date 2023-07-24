package com.example.mygithubuser.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mygithubuser.data.local.DbModule
import com.example.mygithubuser.data.model.ResponseGitHubUser
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dbModule: DbModule) : ViewModel() {


    fun getUserFavorite(): LiveData<MutableList<ResponseGitHubUser.Item>> {
        return dbModule.userDao.loadAll()
    }

    fun deleteUserFavorite(user: ResponseGitHubUser.Item) {
        viewModelScope.launch {
            dbModule.userDao.delete(user)
        }
    }



    class Factory (private val db: DbModule) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}