package com.hrhera.bookapp.ui.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.enums.SignupStatus
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.databinding.FragmentSignUpBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.util.Status
import com.hrhera.bookapp.util.showHint


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).signUpViewModel
        (requireActivity() as MainActivity).supportActionBar?.hide()

        bind.okSignUp.setOnClickListener {
            if (!bind.agreeCheck.isChecked) {
                bind.agreeCheck.error = getString(R.string.required)
                return@setOnClickListener
            }
            val name = bind.getName.text.toString()
            val phone = bind.getPhone.text.toString()
            val email = bind.getEmail.text.toString()
            val password = bind.getPassword.text.toString()
            val user = User(name = name, phone = phone, password = password, email = email)
            model.signUp(user)


        }





        model.signUpLiveData().observe(viewLifecycleOwner, {
            when (it["Status"] as SignupStatus) {
                SignupStatus.LOADING -> {
                    Status.loading()
                }
                SignupStatus.PASSWORD_INVALID -> {
                    Status.normal()
                    bind.getPassword.error = getString(R.string.invalidPassword)
                }
                SignupStatus.PHONE_INVALID -> {
                    Status.normal()
                    bind.getPhone.error = getString(R.string.invalidPhone)

                }
                SignupStatus.PASSWORD_ERROR -> {
                    Status.normal()
                    bind.getPassword.error = getString(R.string.wrongPassword)
                }
                SignupStatus.ALREADY_USER -> {
                    Status.normal()
                    bind.getPhone.error = getString(R.string.wrongPhoneSign)
                }
                SignupStatus.SUCCESS -> {
                    Status.normal()
                    showHint(
                        requireActivity(),
                        getString(R.string.congrats),
                        getString(R.string.congratsLogin)
                    ) { p0, _ ->
                        p0.dismiss()
                        (requireActivity() as MainActivity).navController.navigate(R.id.loginFragment)
                    }
                }
                SignupStatus.SOME_ERROR -> {
                    Status.normal()
                    Toast.makeText(requireContext(), "SomeError", Toast.LENGTH_SHORT).show()
                }
                SignupStatus.NAME_ERROR -> {
                    Status.normal()
                    bind.getName.error = getString(R.string.invalidName)
                }
                SignupStatus.EMAIL_ERROR -> {
                    Status.normal()
                    bind.getEmail.error = getString(R.string.invalidEmail)
                }
            }

        })


        return bind.root

    }

}