package com.wellmember.app.fragment.productTestimonials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wellmember.app.databinding.FragmentProductTestimonialsBinding
import com.wellmember.app.fragment.viewModel.ProductTestimonialsViewModel

class ProductTestimonialsFragment : Fragment() {

    private lateinit var productTestimonialsViewModel: ProductTestimonialsViewModel
    private var _binding: FragmentProductTestimonialsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productTestimonialsViewModel =
            ViewModelProvider(this).get(ProductTestimonialsViewModel::class.java)

        _binding = FragmentProductTestimonialsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProductTestimonials
        productTestimonialsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}