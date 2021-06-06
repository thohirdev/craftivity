package com.garbage.craftivity.ui.craft.list

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.garbage.craftivity.R
import com.garbage.craftivity.databinding.ActivityListCraftBinding
import com.garbage.craftivity.ui.detail.DetailCraftActivity
import com.google.android.material.tabs.TabLayout

class ListCraftActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityListCraftBinding = ActivityListCraftBinding.inflate(layoutInflater)
        setContentView(activityListCraftBinding.root)



        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityListCraftBinding.viewPager.adapter = sectionsPagerAdapter
        activityListCraftBinding.tabs.setupWithViewPager(activityListCraftBinding.viewPager)

        val extraCategory = intent.extras
        if (extraCategory != null) {
            var IdCategory = extraCategory.getInt("CRAFT_CATEGORY")
            Log.e(ContentValues.TAG, "IdCategory: ${IdCategory}")
            val tab: TabLayout.Tab? = activityListCraftBinding.tabs.getTabAt(IdCategory)
            if (tab != null) {
                tab.select()
            }
        }

        supportActionBar?.elevation = 0f
    }
}