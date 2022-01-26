package com.wellmember.app.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenStarted
import androidx.navigation.findNavController
import com.google.android.material.internal.NavigationMenuItemView
import com.wellmember.app.R
import com.wellmember.app.databinding.FragmentHomeBinding
import com.wellmember.app.fragment.viewModel.HomeViewModel
import com.wellmember.app.tool.banner.BannerAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Banner
        val adapter = BannerAdapter()
        binding.banner.apply {
            setLifecycleRegistry(lifecycle)

            setAutoPlay(true)

            setCanLoop(true)

            setInterval(3)

            setAdapter(adapter)
        }.create(
            listOf(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round
            )
        )

        // button redirect
        val toArchive = binding.toArchive
        toArchive.setOnClickListener {
            toArchive.findNavController().navigate(R.id.nav_archive);
        }

        val toBonus = binding.toBonus
        toBonus.setOnClickListener {
            toBonus.findNavController().navigate(R.id.nav_bonus);
        }

        val toProductTestimonials = binding.toProductTestimonials
        toProductTestimonials.setOnClickListener {
            toProductTestimonials.findNavController().navigate(R.id.nav_product_testimonials);
        }

        val toOrderRecord = binding.toOrderRecord
        toOrderRecord.setOnClickListener {
            toOrderRecord.findNavController().navigate(R.id.nav_order_record);
        }

        val toActivitySignIn = binding.toActivitySignIn
        toActivitySignIn.setOnClickListener {
            toActivitySignIn.findNavController().navigate(R.id.nav_activity_sign_in);
        }

        val toForm = binding.toForm
        toForm.setOnClickListener {
            toForm.findNavController().navigate(R.id.nav_form);
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}