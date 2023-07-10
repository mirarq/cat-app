package com.example.catapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.data.repository.MainRepository
import com.example.catapp.data.model.retrofit.ApiHelper

class CatViewModelFactory(private val apiHelper: ApiHelper,private val context:Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            return CatViewModel(MainRepository(apiHelper, context)) as T
        }else if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(MainRepository(apiHelper,context)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}