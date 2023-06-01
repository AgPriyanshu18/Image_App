package com.example.imageapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapp.di.Resource
import com.example.imageapp.models.ImageModel
import com.example.imageapp.repo.ImageAPI
import com.example.imageapp.repo.ImageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val imageRepo: ImageRepo) : ViewModel() {

    private val performFetchImage = MutableLiveData<Resource<ImageModel>>()
    val performFetchImageStatus: LiveData<Resource<ImageModel>>
        get() = performFetchImage


    fun getShops() {
        viewModelScope.launch {
            try {
                performFetchImage.value = Resource.loading()
                val response = imageRepo.getImage()
                if (response.isSuccessful) {
                    performFetchImage.value = Resource.success(response.body()!!)
                } else {
                    performFetchImage.value = Resource.empty()
                }
            } catch (e: Exception) {
                Log.e("Home viewmodel" , " ${e.message}")
                if (e is UnknownHostException) {
                    performFetchImage.value = Resource.offlineError()
                } else {
                    performFetchImage.value = Resource.error(e)
                }
            }
        }
    }

}