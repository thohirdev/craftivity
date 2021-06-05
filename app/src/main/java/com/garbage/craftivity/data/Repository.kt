package com.garbage.craftivity.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.garbage.craftivity.data.room.RemoteDataSource
import com.garbage.craftivity.data.room.response.CraftResponse

class Repository private constructor(private val remoteDataSource : RemoteDataSource): DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource)
            }
    }

    private val loading = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean> = loading

    override fun getCraft(): LiveData<ArrayList<CraftResponse>> {
        loading.value = true
        val dataCraft = MutableLiveData<ArrayList<CraftResponse>>()
        remoteDataSource.getCraft(object : RemoteDataSource.LoadCallback {
            override fun getAllData(response: ArrayList<CraftResponse>?) {
                dataCraft.postValue(response)
                loading.postValue(false)
            }
        })
        return dataCraft
    }
}