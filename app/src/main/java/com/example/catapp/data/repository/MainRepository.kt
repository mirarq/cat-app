package com.example.catapp.data.repository

import android.content.Context
import com.example.catapp.data.model.Cat
import com.example.catapp.data.model.retrofit.ApiHelper
import com.example.catapp.data.model.room.CatDatabase

class MainRepository(
    private val apiHelper: ApiHelper,
    private val context: Context
) {
    private var database = CatDatabase.getInstance(context)?.dao()
    suspend fun getRandomCat() = apiHelper.getRandomCat()
    fun getFavoriteCats() = database?.getFavoriteCats()
    suspend fun insertCat(cat:Cat){
        database?.insertCat(cat)
    }
    suspend fun deleteCat(cat:Cat){
        database?.deleteCat(cat)
    }
}