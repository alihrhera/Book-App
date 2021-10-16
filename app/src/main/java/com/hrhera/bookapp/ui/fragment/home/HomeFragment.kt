package com.hrhera.bookapp.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.hrhera.bookapp.R
import com.hrhera.bookapp.databinding.FragmentHomeBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.adapter.BookAdapter
import com.hrhera.bookapp.ui.adapter.CategoryAdapter
import com.hrhera.bookapp.ui.adapter.SliderAdapter


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        (requireActivity() as MainActivity).navView?.visibility = View.VISIBLE
        model = (requireActivity() as MainActivity).homeViewModel

        val popularAdapter = BookAdapter()
        bind.popularRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        bind.popularRecycler.adapter = popularAdapter

        val recommendedAdapter = BookAdapter()

        bind.recommendedRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        bind.recommendedRecycler.adapter = recommendedAdapter


        val categoryAdapter = CategoryAdapter()

        bind.catRecycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        bind.catRecycler.adapter = categoryAdapter


        val sliderAdapter = SliderAdapter()
        bind.sliderView.adapter = sliderAdapter

        bind.sliderView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                setIndicatorBack(position)

            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        }
        )

        bind.sliderView.currentItem = 0
        model.popularLiveData().observe(viewLifecycleOwner, {
            bind.popLoader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                return@observe
            }
            bind.popLoader.visibility = View.GONE
            popularAdapter.submitList(it)
        })

        model.recommendedLiveData().observe(viewLifecycleOwner, {

            bind.recommendedLoader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                return@observe
            }
            bind.recommendedLoader.visibility = View.GONE
            recommendedAdapter.submitList(it)
        })

        model.categoryMuLiveDataIcon.observe(viewLifecycleOwner, {
            bind.catLoader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                return@observe
            }
            bind.catLoader.visibility = View.GONE
            categoryAdapter.submitList(it)
        })


        model.sliderLiveData().observe(viewLifecycleOwner, {
            bind.sliderLoader.visibility = View.VISIBLE
            if (it.isEmpty()) {
                return@observe
            }
            bind.sliderLoader.visibility = View.GONE
            dataList.clear()
            for (i in it) {
                dataList.add(i.photo)
            }
            sliderAdapter.setDataList(dataList)
            createIndicator()
            bind.indicator
        })


        return bind.root

    }

    private val dataList = mutableListOf<String>()
    private val listOfDots = mutableListOf<ImageView>()

    private fun setIndicatorBack(pos: Int) {
        for ((i, x) in listOfDots.withIndex()) {
            x.setImageResource(R.drawable.non_selected_tap)
            if (i == pos) {
                x.setImageResource(R.drawable.selected_tap)
            }
        }
    }

    private fun createIndicator() {
        listOfDots.clear()
        for (i in 0 until dataList.size) {
            val dot = ImageView(requireContext())
            listOfDots.add(dot)
            dot.setImageResource(R.drawable.non_selected_tap)
            bind.indicator.addView(dot)
        }
        if (listOfDots.isNotEmpty()) {
            listOfDots[0].setImageResource(R.drawable.selected_tap)
        }
    }

}