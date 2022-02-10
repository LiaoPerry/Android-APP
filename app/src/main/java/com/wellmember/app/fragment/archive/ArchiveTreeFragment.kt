package com.wellmember.app.fragment.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wellmember.app.databinding.FragmentArchiveOverviewBinding
import com.wellmember.app.databinding.FragmentArchiveTreeBinding
import com.wellmember.app.fragment.viewModel.ArchiveTreeFragmentViewModel

class ArchiveTreeFragment : Fragment(){

    private lateinit var archiveTreeFragmentViewModel: ArchiveTreeFragmentViewModel
    private var _binding: FragmentArchiveTreeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        archiveTreeFragmentViewModel =
            ViewModelProvider(this).get(ArchiveTreeFragmentViewModel::class.java)

        _binding = FragmentArchiveTreeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textArchiveTree
        archiveTreeFragmentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}