package com.garbage.craftivity.ui.craft

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.garbage.craftivity.R
import com.garbage.craftivity.data.room.response.CraftResponse
import com.garbage.craftivity.databinding.FragmentCraftBinding
import com.garbage.craftivity.viewmodel.FactoryViewModel

class CraftFragment : Fragment(), CraftAdapter.FragmentCallback {

    private lateinit var binding: FragmentCraftBinding
    private lateinit var viewModel: CraftViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCraftBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = FactoryViewModel.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[CraftViewModel::class.java]

        showCraft()
    }

    override fun onShareClick(craft: CraftResponse) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share This App Now !!")
                .setText(resources.getString(R.string.share, craft.title))
                .startChooser()
        }
    }

    private fun showCraft(){
        val craft = viewModel.getCraft()
        val match = CraftAdapter(this)

        craft.observe(viewLifecycleOwner, { craft ->
            Log.e("Craft", craft.toString())
            match.setCraft(craft)
        })

        viewModel.getProgressLoad().observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        with(binding.rvCraft) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = match
        }
    }
}