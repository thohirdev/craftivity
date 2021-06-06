package com.garbage.craftivity.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.garbage.craftivity.databinding.FragmentHomeBinding
import com.garbage.craftivity.ui.home.menu.CraftInformationActivity
import com.garbage.craftivity.ui.home.menu.InformationActivity
import com.garbage.craftivity.ui.home.menu.TypeTrashActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemInformation.setOnClickListener {
            val intent = Intent(context, InformationActivity::class.java)
            binding.itemInformation.context.startActivity(intent)
        }

        binding.itemCraft.setOnClickListener {
            val intent = Intent(context, CraftInformationActivity::class.java)
            binding.itemCraft.context.startActivity(intent)
        }

        binding.itemTypeTrash.setOnClickListener {
            val intent = Intent(context, TypeTrashActivity::class.java)
            binding.itemTypeTrash.context.startActivity(intent)
        }

        binding.itemContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("craftivity@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Email for Craftivity")
            intent.putExtra(Intent.EXTRA_TEXT, "Hallo, ")
            try {
                startActivity(Intent.createChooser(intent, "Do You Want to Send Email ?"))
            } catch (ex: ActivityNotFoundException) {
                //do something else
            }
        }
    }
}