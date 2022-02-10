package com.wellmember.app.fragment.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wellmember.app.databinding.FragmentArchiveBinding
import com.wellmember.app.fragment.viewModel.ArchiveViewModel

class ArchiveFragment : Fragment() {

    private lateinit var archiveViewModel: ArchiveViewModel
    private var _binding: FragmentArchiveBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        archiveViewModel =
            ViewModelProvider(this).get(ArchiveViewModel::class.java)

        _binding = FragmentArchiveBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val pageAdapter = binding.archiveView
        val  fragmentManager = (activity as FragmentActivity).supportFragmentManager
        pageAdapter.adapter = ArchiveFragmentStateAdapter(fragmentManager, lifecycle)

        val title: ArrayList<String> = arrayListOf("業績總覽", "樹狀業績")
        val tabLayout = binding.viewTable
        val popText = binding.popAreaText
        val popBtn = binding.popAreaBtn
        val scale = resources.displayMetrics.density
        val cardView = binding.cardView

        TabLayoutMediator(tabLayout, binding.archiveView) { tab, position ->
            tab.text = title[position]
        }.attach()


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                if(tabLayout.selectedTabPosition == 1){
                    hideView(popText)
                    hideView(popBtn)
                }
                else if(tabLayout.selectedTabPosition == 0){
                    showHide(popText)
                    showHide(popBtn)
                }

                if(!((popBtn.isVisible) && (popText.isVisible))){

                    pageAdapter.layoutParams.height = (700 * scale).toInt()
                    pageAdapter.layoutParams.width = MATCH_PARENT
                    pageAdapter.requestLayout()

                    cardView.layoutParams.height = (75 * scale).toInt()
                    cardView.layoutParams.width = MATCH_PARENT
                    cardView.requestLayout()

                }
                else if((popBtn.isVisible) && (popText.isVisible)){

                    pageAdapter.layoutParams.width = MATCH_PARENT
                    pageAdapter.layoutParams.height = (500 * scale).toInt()
                    pageAdapter.requestLayout()

                    cardView.layoutParams.height = WRAP_CONTENT
                    cardView.layoutParams.width = MATCH_PARENT
                    cardView.requestLayout()

                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
                if(tabLayout.selectedTabPosition == 0){

                    showHide(popText)
                    showHide(popBtn)
                    if(!((popBtn.isVisible) && (popText.isVisible))){

                        pageAdapter.layoutParams.height = (700 * scale).toInt()
                        pageAdapter.layoutParams.width = MATCH_PARENT
                        pageAdapter.requestLayout()

                        cardView.layoutParams.height = (75 * scale).toInt()
                        cardView.layoutParams.width = MATCH_PARENT
                        cardView.requestLayout()

                    }
                    else if((popBtn.isVisible) && (popText.isVisible)){

                        pageAdapter.layoutParams.width = MATCH_PARENT
                        pageAdapter.layoutParams.height = (500 * scale).toInt()
                        pageAdapter.requestLayout()

                        cardView.layoutParams.height = WRAP_CONTENT
                        cardView.layoutParams.width = MATCH_PARENT
                        cardView.requestLayout()

                    }

                } // end of selectTab
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect

            }
        })

        return root
    }

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }

    fun hideView(view:View) {
        if (view.visibility == View.VISIBLE){
            view.visibility = View.INVISIBLE
        }

    }

    fun dynamicDime(view:View){

        if(view.visibility == View.INVISIBLE){

            view.layoutParams.height = 300
            view.layoutParams.width = 200
            view.requestLayout()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}