package com.example.catapp.ui.mainfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catapp.CatApplication
import com.example.catapp.CatViewModel
import com.example.catapp.CatViewModelFactory
import com.example.catapp.FavoriteViewModel
import com.example.catapp.data.model.Cat
import com.example.catapp.data.model.retrofit.ApiHelper
import com.example.catapp.data.model.retrofit.RetrofitBuilder
import com.example.catapp.databinding.FragmentMainBinding
import com.example.catapp.utills.Status
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var catViewModel: CatViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var randomCat: Cat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModels()
        setupObserversCat()
        clickNext()

    }

    private fun setupViewModels() {
        catViewModel = ViewModelProvider(
            this,
            CatViewModelFactory(CatApplication().apiHelper, requireContext().applicationContext)
        )[CatViewModel::class.java]
        favoriteViewModel = ViewModelProvider(
            this,CatViewModelFactory(CatApplication().apiHelper,requireContext().applicationContext)
        )[FavoriteViewModel::class.java]
    }

    private fun setupObserversCat(){
        catViewModel.getRandomCat().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        randomCat = resource.data!![0]
                        bindCat(randomCat)
                        Log.d("MyLog","Success: ${resource.data}")
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context,"${resource.message}",Toast.LENGTH_LONG).show()
                        Log.d("MyLog","Error: ${resource.message}")
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Log.d("MyLog","Loading")
                    }
                }
            }
        })
    }

    private fun bindCat(cat:Cat)= with(binding){
        Picasso.get().load(cat.url).into(ivRandomCat)
    }
    private fun clickNext() = with(binding){
        btnNext.setOnClickListener {
            setupObserversCat()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()

    }
}