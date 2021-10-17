package com.hrhera.bookapp.ui.fragment.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.databinding.FragmentProfileBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.util.DataManger


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: ProfileViewModel
    private val categories = DataManger.listOfBookCategory
    private val listOfCategory = mutableListOf<BookCategory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).profileViewModel

        model.userLiveData().observe(viewLifecycleOwner, {
            val userName = it.name.split(" ")
            bind.userName.text = if (userName.size > 1) {
                (""" ${userName[0]}
                |${userName[1]}
            """.trimMargin())
            } else {
                it.name
            }


            bind.userPhone.text = (" ${it.phone}")
            bind.userEmail.text = (" ${it.email}")
            var userInterests = it.setOfInterests
            userInterests = if (userInterests.isNotEmpty()) {
                it.setOfInterests as MutableList
            } else {
                mutableListOf()
            }

            listOfCategory.clear()
            listOfCategory.addAll(categories)
            for (c in categories) {
                val cc=BookCategory(name = c.name+" 1")
                listOfCategory.add(cc)
            }
            bind.userInterests.removeAllViews()
            for (i in listOfCategory) {
                val chip = Chip(requireContext())
                chip.text = i.name
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                chip.setChipBackgroundColorResource(R.color.kGray)
                if (userInterests.contains(i)) {
                    chip.setChipBackgroundColorResource(R.color.kSecondaryVariant)
                    chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }
                chip.setOnClickListener { _ ->
                    if (userInterests.contains(i)) {
                        userInterests.remove(i)
                    } else {
                        userInterests.add(i)
                    }
                    it.setOfInterests = userInterests
                    model.updateUser(it)
                }

                bind.userInterests.addView(chip)
            }
        })

        return bind.root

    }

}