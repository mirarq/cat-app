package com.example.catapp

import android.app.Application
import com.example.catapp.data.model.retrofit.ApiHelper
import com.example.catapp.data.model.retrofit.RetrofitBuilder
import com.example.catapp.data.model.room.CatDatabase
import com.example.catapp.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CatApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CatDatabase.getInstance(applicationContext) }
    val apiService = RetrofitBuilder.apiService
    val apiHelper = ApiHelper(apiService)
    val repository by lazy {MainRepository(apiHelper,applicationContext)}
}