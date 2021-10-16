package com.hrhera.bookapp.ui.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.databinding.FragmentSignUpBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.fragment.login.LoginViewModel


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).loginViewModel
        (requireActivity() as MainActivity).supportActionBar?.hide()




        return bind.root

    }

}