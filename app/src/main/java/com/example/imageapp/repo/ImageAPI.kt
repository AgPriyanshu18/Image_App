package com.example.imageapp.repo

import com.example.imageapp.models.ImageModel
import retrofit2.Response
import retrofit2.http.GET

interface ImageAPI {

    @GET("photos")
    suspend fun getImages(): Response<ImageModel>
}