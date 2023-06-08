package com.example.imageapp.repo

import com.example.imageapp.models.ImageModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ImageAPI {

    @GET("photos")
    suspend fun getImages(): Response<ImageModel>

    @POST("photos")
    suspend fun postImages(@Body ImageModel : ImageModel): Response<ImageModel>
}