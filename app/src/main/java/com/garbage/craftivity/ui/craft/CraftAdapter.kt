package com.garbage.craftivity.ui.craft

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.garbage.craftivity.R
import com.garbage.craftivity.data.room.response.CraftResponse
import com.garbage.craftivity.databinding.ItemsCraftBinding

class CraftAdapter(private val callback: FragmentCallback) : RecyclerView.Adapter<CraftAdapter.CraftViewHolder>() {

    private var listCraft = ArrayList<CraftResponse>()

    fun setCraft(craft: List<CraftResponse>) {
        this.listCraft.clear()
        this.listCraft.addAll(craft)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CraftViewHolder {
        val itemsMoviesBinding = ItemsCraftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CraftViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: CraftViewHolder, position: Int) {
        holder.bind(listCraft[position])
    }

    override fun getItemCount(): Int = listCraft.size

    inner class CraftViewHolder(private val binding: ItemsCraftBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(craft: CraftResponse) {
            with(binding) {
                Glide.with(itemView)
                    .load(craft.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)

                txtItemTitle.text = craft.title
                txtAuthor.text = craft.author

                imgShare.setOnClickListener { callback.onShareClick(craft) }

                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCraftActivity::class.java)
                    intent.putExtra(EXTRA_DATA, craft.craftId)
                    itemView.context.startActivity(intent)
                }*/
            }
        }
    }

    interface FragmentCallback {
        fun onShareClick(craft: CraftResponse)
    }
}