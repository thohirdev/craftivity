package com.garbage.craftivity.ui.craft

import androidx.lifecycle.ViewModel
import com.garbage.craftivity.data.local.CraftEntity
import com.garbage.craftivity.utils.DataCraft

class CraftViewModel : ViewModel() {

    fun getCraft(): List<CraftEntity> = DataCraft.generateCraft()

}