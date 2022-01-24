package com.wellmember.app.fragment.orderRecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wellmember.app.databinding.FragmentOrderRecordBinding
import com.wellmember.app.fragment.viewModel.OrderRecoredViewModel

class OrderRecordFragment : Fragment() {

    private lateinit var orderRecoredViewModel: OrderRecoredViewModel
    private var _binding: FragmentOrderRecordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        orderRecoredViewModel =
            ViewModelProvider(this).get(OrderRecoredViewModel::class.java)

        _binding = FragmentOrderRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textOrderRecord
        orderRecoredViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}