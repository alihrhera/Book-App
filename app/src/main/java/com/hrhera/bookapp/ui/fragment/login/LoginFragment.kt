package com.hrhera.bookapp.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.R
import com.hrhera.bookapp.databinding.FragmentLoginBinding
import com.hrhera.bookapp.ui.MainActivity


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).loginViewModel
        (requireActivity() as MainActivity).supportActionBar?.hide()
        bind.goToSignUp.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.signUpFragment)
        }

        bind.okLogin.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.homeFragment)

        }


        return bind.root

    }

}