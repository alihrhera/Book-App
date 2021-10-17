package com.hrhera.bookapp.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.enums.LoginStatus
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.databinding.FragmentLoginBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.util.Status


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
            model.login(bind.getPhone.text.toString(), bind.getPassword.text.toString())
        }

        model.loginLiveData().observe(viewLifecycleOwner, {
            when (it["Status"] as LoginStatus) {
                LoginStatus.LOADING -> {
                    Status.loading()
                }
                LoginStatus.PASSWORD_INVALID -> {
                    Status.normal()
                    bind.getPassword.error=getString(R.string.invalidPassword)
                }
                LoginStatus.PHONE_INVALID -> {
                    Status.normal()
                    bind.getPhone.error=getString(R.string.invalidPhone)

                }
                LoginStatus.PASSWORD_ERROR -> {
                    Status.normal()
                    bind.getPassword.error=getString(R.string.wrongPassword)
                }
                LoginStatus.PHONE_ERROR -> {
                    Status.normal()
                    bind.getPhone.error=getString(R.string.wrongPhone)
                }
                LoginStatus.SUCCESS -> {
                    Status.normal()
                    (requireActivity() as MainActivity).navController.navigate(R.id.homeFragment)
                    (requireActivity() as MainActivity). afterLoginInit(it["data"] as User)
                }
                LoginStatus.SOME_ERROR -> {
                    Status.normal()
                    Toast.makeText(requireContext(), "SomeError", Toast.LENGTH_SHORT).show()
                }
            }

        })



        return bind.root

    }

}