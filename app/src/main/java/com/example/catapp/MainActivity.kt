package com.example.catapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.data.model.retrofit.ApiHelper
import com.example.catapp.data.model.retrofit.RetrofitBuilder
import com.example.catapp.ui.favoritefragment.FavoriteFragment
import com.example.catapp.ui.mainfragment.MainFragment
import com.example.catapp.utills.Status


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CatViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(MainFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadFragment(MainFragment())
                    true
                }
                R.id.favorite -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                else ->
                    true
            }
        }

    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}