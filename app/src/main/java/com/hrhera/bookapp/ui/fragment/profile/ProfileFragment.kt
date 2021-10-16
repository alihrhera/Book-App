package com.hrhera.bookapp.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.databinding.FragmentProfileBinding
import com.hrhera.bookapp.ui.MainActivity


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).profileViewModel

        return bind.root

    }

}