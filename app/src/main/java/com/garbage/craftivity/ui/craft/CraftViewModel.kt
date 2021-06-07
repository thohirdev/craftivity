package com.garbage.craftivity.ui.craft

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.garbage.craftivity.data.Repository
import com.garbage.craftivity.data.room.response.CraftResponse

class CraftViewModel (private val repository: Repository): ViewModel() {

    fun getCraft(): LiveData<ArrayList<CraftResponse>> = repository.getCraft()

    fun getCraftCardboard(): LiveData<ArrayList<CraftResponse>> = repository.getCraftCardboard()

    fun getCraftGlass(): LiveData<ArrayList<CraftResponse>> = repository.getCraftGlass()

    fun getCraftMetal(): LiveData<ArrayList<CraftResponse>> = repository.getCraftMetal()

    fun getCraftPaper(): LiveData<ArrayList<CraftResponse>> = repository.getCraftPaper()

    fun getCraftPlastic(): LiveData<ArrayList<CraftResponse>> = repository.getCraftPlastic()

    fun getProgressLoad() : LiveData<Boolean> = repository.loadData

}