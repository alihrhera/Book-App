package com.hrhera.bookapp.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.databinding.FragmentHomeBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.fragment.home.FavoriteViewModel


class FavoriteFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).favoriteViewModel






        return bind.root

    }

}