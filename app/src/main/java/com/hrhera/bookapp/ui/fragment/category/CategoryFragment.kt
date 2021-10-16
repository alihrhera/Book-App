package com.hrhera.bookapp.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.databinding.FragmentCategoryBinding
import com.hrhera.bookapp.databinding.FragmentHomeBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.fragment.home.FavoriteViewModel


class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: CategoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).categoryViewModel

        return bind.root

    }

}