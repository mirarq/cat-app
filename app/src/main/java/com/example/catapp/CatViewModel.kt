package com.example.catapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.catapp.data.repository.MainRepository
import com.example.catapp.utills.Resource
import kotlinx.coroutines.Dispatchers

class CatViewModel ( private val mainRepository: MainRepository): ViewModel(){
    fun getRandomCat() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getRandomCat()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}