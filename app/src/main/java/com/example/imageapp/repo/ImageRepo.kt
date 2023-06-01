package com.example.imageapp.repo

import javax.inject.Inject


class ImageRepo @Inject constructor(private val imageAPI: ImageAPI) {

    suspend fun getImage() =imageAPI.getImages()

}