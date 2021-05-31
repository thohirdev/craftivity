package com.garbage.craftivity.ui.craft

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.garbage.craftivity.R
import com.garbage.craftivity.data.local.CraftEntity
import com.garbage.craftivity.databinding.ItemsCraftBinding
import com.garbage.craftivity.ui.detail.DetailCraftActivity
import com.garbage.craftivity.ui.detail.DetailCraftActivity.Companion.EXTRA_DATA

class CraftAdapter(private val callback: FragmentCallback) : RecyclerView.Adapter<CraftAdapter.HomeViewHolder>() {

    private var listCraft = ArrayList<CraftEntity>()

    fun setCraft(craft: List<CraftEntity>?) {
        if (craft == null) return
        this.listCraft.clear()
        this.listCraft.addAll(craft)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemsMoviesBinding = ItemsCraftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val craft = listCraft[position]
        holder.bind(craft)
    }

    override fun getItemCount(): Int = listCraft.size

    inner class HomeViewHolder(private val binding: ItemsCraftBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(craft: CraftEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load(craft.imageCraft)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                    .into(imgPoster)
                txtItemTitle.text = craft.title
                txtAuthor.text = craft.author
                imgShare.setOnClickListener { callback.onShareClick(craft) }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCraftActivity::class.java)
                    intent.putExtra(EXTRA_DATA, craft.idCraft)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    interface FragmentCallback {
        fun onShareClick(craft: CraftEntity)
    }
}