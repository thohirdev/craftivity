package com.garbage.craftivity.ui.detail

import androidx.lifecycle.ViewModel
import com.garbage.craftivity.data.local.CraftEntity
import com.garbage.craftivity.utils.DataCraft

class DetailCraftViewModel : ViewModel() {
    //private var idCraft: Int = 0

    private lateinit var idCraft: String

    fun setSelected(idCraft: String) {
        this.idCraft = idCraft
    }

    fun getCraft(): CraftEntity {
        lateinit var craft: CraftEntity
        val craftEntities = DataCraft.generateCraft()
        for (CraftEntity in craftEntities) {
            if (CraftEntity.idCraft == idCraft) {
                craft = CraftEntity
            }
        }
        return craft
    }
}