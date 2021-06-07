package com.garbage.craftivity.data.room.api

import com.garbage.craftivity.data.room.response.CraftResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("apicrafts")
    fun getCraft(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/category/cardboard")
    fun getCategoryCardboard(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/category/glass")
    fun getCategoryGlass(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/category/metal")
    fun getCategoryMetal(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/category/paper")
    fun getCategoryPaper(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/category/plastic")
    fun getCategoryPlastic(): Call<ArrayList<CraftResponse>>
}