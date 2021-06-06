package com.garbage.craftivity.ui.craft.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.garbage.craftivity.databinding.ActivityListCraftBinding

class ListCraftActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityListCraftBinding = ActivityListCraftBinding.inflate(layoutInflater)
        setContentView(activityListCraftBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityListCraftBinding.viewPager.adapter = sectionsPagerAdapter
        activityListCraftBinding.tabs.setupWithViewPager(activityListCraftBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}