package com.garbage.craftivity.data.room

import android.content.ContentValues.TAG
import android.util.Log
import com.garbage.craftivity.data.room.api.ApiConfig
import com.garbage.craftivity.data.room.response.CraftResponse
import com.garbage.craftivity.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object{
        @Volatile
        private var instance : RemoteDataSource?= null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource()
        }
    }

    fun getCraft(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCraft().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun getCraftCardboard(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCategoryCardboard().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun getCraftGlass(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCategoryGlass().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun getCraftMetal(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCategoryMetal().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun getCraftPaper(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCategoryPaper().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun getCraftPlastic(callback : LoadCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getCategoryPlastic().enqueue(object : Callback<ArrayList<CraftResponse>>{
            override fun onResponse(call: Call<ArrayList<CraftResponse>>, response: Response<ArrayList<CraftResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.getAllData(it)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<ArrayList<CraftResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    interface LoadCallback {
        fun getAllData(response: ArrayList<CraftResponse>?)
    }
}