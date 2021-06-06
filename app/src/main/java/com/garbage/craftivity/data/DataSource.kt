package com.garbage.craftivity.data

import androidx.lifecycle.LiveData
import com.garbage.craftivity.data.room.response.CraftResponse

interface DataSource {

    fun getCraft(): LiveData<ArrayList<CraftResponse>>

    fun getCraftPaper(): LiveData<ArrayList<CraftResponse>>

    fun getCraftGlass(): LiveData<ArrayList<CraftResponse>>
}