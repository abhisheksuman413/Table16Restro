package com.fps69.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiDummyDataInterface {
    @GET("recipes")
    fun getProductData(): Call<DummeyUserData>
}