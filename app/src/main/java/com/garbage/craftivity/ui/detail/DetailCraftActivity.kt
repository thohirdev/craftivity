package com.garbage.craftivity.ui.detail

import android.os.Bundle
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
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    private lateinit var binding: ActivityDetailCraftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCraftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCraftViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val idCraft = extras.getString(EXTRA_DATA)
            if (idCraft != null) {
                viewModel.setSelected(idCraft)
                getDetail(viewModel.getCraft())
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