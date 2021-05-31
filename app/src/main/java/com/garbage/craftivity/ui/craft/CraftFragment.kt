package com.garbage.craftivity.ui.craft

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.garbage.craftivity.R
import com.garbage.craftivity.data.local.CraftEntity
import com.garbage.craftivity.databinding.FragmentCraftBinding

class CraftFragment : Fragment(), CraftAdapter.FragmentCallback {

    private lateinit var binding: FragmentCraftBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCraftBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[CraftViewModel::class.java]
            val craft = viewModel.getCraft()

            val dashboardAdapter = CraftAdapter(this)
            dashboardAdapter.setCraft(craft)

            with(binding.rvCraft) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = dashboardAdapter
            }
        }
    }

    override fun onShareClick(craft: CraftEntity) {
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
}