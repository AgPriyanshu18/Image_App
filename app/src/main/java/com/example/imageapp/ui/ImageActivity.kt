package com.example.imageapp.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.imageapp.R
import com.example.imageapp.databinding.ActivityImageBinding
import com.example.imageapp.di.Resource
import com.example.imageapp.models.ImageModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {

    lateinit var binding : ActivityImageBinding
    lateinit var viewModel : MainViewModel
    private var ImageList : ImageModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getShops()

        setObservers()
    }

    private fun setObservers(){
        viewModel.performFetchImageStatus.observe(this, Observer {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.EMPTY -> {
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.GONE
                    ImageList = it.data
                    setUpRecyclerView()
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.VISIBLE
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        })
    }

    private fun setUpRecyclerView() {
        val adapter = ImageAdapter(this, ImageList!!.data)
        binding.searchResultRv.adapter = adapter
    }

}