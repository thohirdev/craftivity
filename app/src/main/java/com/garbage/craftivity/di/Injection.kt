package com.garbage.craftivity.di

import android.content.Context
import com.garbage.craftivity.data.Repository
import com.garbage.craftivity.data.room.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}