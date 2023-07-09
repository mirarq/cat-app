package com.example.catapp.data.model.retrofit

class ApiHelper(private val apiService: ApiService) {
    suspend fun getRandomCat() = apiService.getRandomCat()
}