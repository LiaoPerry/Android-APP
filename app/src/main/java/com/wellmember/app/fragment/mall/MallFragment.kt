package com.wellmember.app.fragment.mall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wellmember.app.databinding.FragmentMallBinding
import com.wellmember.app.fragment.viewModel.MallViewModel


class MallFragment : Fragment() {

    private lateinit var mallViewModel: MallViewModel
    private var _binding: FragmentMallBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mallViewModel =
            ViewModelProvider(this).get(MallViewModel::class.java)

        _binding = FragmentMallBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMall
        mallViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}