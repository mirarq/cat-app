package com.example.catapp.data.repository

import com.example.catapp.data.model.retrofit.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getRandomCat() = apiHelper.getRandomCat()
}