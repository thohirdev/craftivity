package com.garbage.craftivity.ui.craft

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.garbage.craftivity.data.Repository
import com.garbage.craftivity.data.room.response.CraftResponse

class CraftViewModel (private val repository: Repository): ViewModel() {

    fun getCraft(): LiveData<ArrayList<CraftResponse>> = repository.getCraft()

    fun getProgressLoad() : LiveData<Boolean> = repository.loadData

}