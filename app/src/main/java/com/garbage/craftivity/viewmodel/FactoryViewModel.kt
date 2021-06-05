package com.garbage.craftivity.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garbage.craftivity.data.Repository
import com.garbage.craftivity.di.Injection
import com.garbage.craftivity.ui.craft.CraftViewModel

class FactoryViewModel private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: FactoryViewModel? = null

        fun getInstance(context: Context): FactoryViewModel = instance ?: synchronized(this) {
            instance ?: FactoryViewModel(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }

    @SuppressLint
    ("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(CraftViewModel::class.java) -> {
                return CraftViewModel(repository) as T
            }
            /*modelClass.isAssignableFrom(DetailCraftViewModel::class.java) -> {
                return DetailCraftViewModel(repository) as T
            }*/
            else -> throw Throwable("Unknown ViewModel Class : " + modelClass.name)
        }
    }
}