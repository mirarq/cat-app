package com.example.catapp.data.model.retrofit

import com.example.catapp.data.model.Cat
import retrofit2.http.GET

interface ApiService {
    @GET("images/search")
     suspend fun getRandomCat(): List<Cat>
}