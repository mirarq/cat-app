package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.data.model.retrofit.ApiHelper
import com.example.catapp.data.model.retrofit.RetrofitBuilder
import com.example.catapp.utills.Status


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CatViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        binding.button.setOnClickListener {
            setupObservers()
        }

    }
    private fun setupViewModel(){
        viewModel = ViewModelProvider(
            this,
            CatViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[CatViewModel::class.java]
    }
    private fun setupObservers() {
        viewModel.getRandomCat().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("MyLog", "Success: ${resource.data}")
                    }

                    Status.ERROR -> {
                        Log.d("MyLog", "Error: ${resource.message}")
                    }

                    Status.LOADING -> {
                        Log.d("MyLog", "Loading")
                    }
                }
            }
        })
    }
}