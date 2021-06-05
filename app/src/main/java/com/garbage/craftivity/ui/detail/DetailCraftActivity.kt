package com.garbage.craftivity.ui.detail

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.garbage.craftivity.R
import com.garbage.craftivity.data.local.CraftEntity
import com.garbage.craftivity.databinding.ActivityDetailCraftBinding

class DetailCraftActivity : AppCompatActivity() {

    companion object {
        const val CRAFT_ID = "CRAFT_ID"
        const val THUMBNAIL_CRAFT = "THUMBNAIL_CRAFT"
        const val TITLE_CRAFT = "TITLE_CRAFT"
        const val AUTHOR_CRAFT = "AUTHOR_CRAFT"
        const val CATEGORY_CRAFT = "CATEGORY_CRAFT"
        const val RATE_CRAFT = "RATE_CRAFT"
        const val SUPPLIES_CRAFT = "SUPPLIES_CRAFT"
        const val INSTRUCTIONS_CRAFT = "INSTRUCTIONS_CRAFT"
    }

    private lateinit var binding: ActivityDetailCraftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCraftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCraftViewModel::class.java]

        val extraDetail = intent.extras
        Log.e(ContentValues.TAG, "extraDetail: ${extraDetail}")
        if (extraDetail != null) {
            val idCraft = extraDetail.getInt(CRAFT_ID)
            if (idCraft != null) {
                binding.txtTitle.text = extraDetail.getString(TITLE_CRAFT)
                binding.txtAuthor.text = extraDetail.getString(AUTHOR_CRAFT)
                binding.txtCategory.text = extraDetail.getString(CATEGORY_CRAFT)
                binding.txtRating.text = extraDetail.getString(RATE_CRAFT)
                binding.txtSupplies.text = extraDetail.getString(SUPPLIES_CRAFT)
                binding.txtInstruction.text = extraDetail.getString(INSTRUCTIONS_CRAFT)

                Glide.with(this)
                    .load(extraDetail.getString(THUMBNAIL_CRAFT))
                    .apply(RequestOptions().placeholder(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(binding.imagePhoto)
            }
        }
    }

    private fun getDetail(craft: CraftEntity) {
        binding.txtTitle.text = craft.title
        binding.txtAuthor.text = craft.author
        binding.txtRating.text = craft.rate
        binding.txtSupplies.text = craft.supplies
        binding.txtInstruction.text = craft.instruction

        Glide.with(this)
            .load(craft.imageCraft)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(binding.imagePhoto)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}