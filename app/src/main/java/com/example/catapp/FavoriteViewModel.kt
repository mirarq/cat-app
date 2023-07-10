package com.example.catapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.model.Cat
import com.example.catapp.data.repository.MainRepository
import com.example.catapp.utills.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getFavoriteCats() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(mainRepository.getFavoriteCats()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred!"))
        }
    }

    fun insertCat(cat: Cat) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.insertCat(cat)
    }

    fun deleteCat(cat: Cat) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.deleteCat(cat)
    }
}