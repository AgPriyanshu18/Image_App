package com.example.imageapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
                    e("setObservers", "Loading")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.EMPTY -> {
                    e("setObservers", "Empty")
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    e("setObservers", "Success")
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.GONE
                    ImageList = it.data
                    setUpRecyclerView()
                }
                Resource.Status.ERROR -> {
                    e("setObservers", "Error")
                    binding.progressBar.visibility = View.GONE
                    binding.emptyDialog.visibility = View.VISIBLE
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        })
    }

    private fun setUpRecyclerView() {
        e("setUpRecyclerView", ImageList.toString())
        val adapter = ImageAdapter( ImageList!!, object : ImageAdapter.onClickListener{
            override fun onClick(position: Int) {
                //TODO
            }
        })
        binding.searchResultRv.setHasFixedSize(true)
        binding.searchResultRv.layoutManager = LinearLayoutManager(this)
        binding.searchResultRv.adapter = adapter
    }



}