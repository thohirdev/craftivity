package com.garbage.craftivity.data.room.api

import com.garbage.craftivity.data.room.response.CraftResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("apicrafts")
    fun getCraft(): Call<ArrayList<CraftResponse>>

    @GET("apicrafts/{category}")
    fun getCategoryCraft(): Call<ArrayList<CraftResponse>>
    
}