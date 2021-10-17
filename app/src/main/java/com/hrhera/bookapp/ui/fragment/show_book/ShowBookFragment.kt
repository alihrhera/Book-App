package com.hrhera.bookapp.ui.fragment.show_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.databinding.FragmentBookInfoBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.util.Status
import com.squareup.picasso.Picasso


class ShowBookFragment : Fragment() {
    private var _binding: FragmentBookInfoBinding? = null
    private val bind get() = _binding!!

    private lateinit var model: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookInfoBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).bookViewModel

        model.oneBookLiveData().observe(viewLifecycleOwner, {
            Picasso.get().load(it.photo).fit().centerCrop().into(bind.bookImage)
            bind.bookName.text = it.name

            bind.bookDisc.text = if (it.details.isEmpty()) {
                getText(R.string.text_disc)
            } else {
                it.details
            }
            bind.likeIt.setImageResource(R.drawable.ic_like_it)
            if (it.listOfLikes.contains(AppUser.getPhone())) {
                bind.likeIt.setImageResource(R.drawable.ic_liked)
            }
            bind.likeIt.setOnClickListener { _ ->
                if (it.listOfLikes.contains(AppUser.getPhone())) {
                    it.listOfLikes.remove(AppUser.getPhone())
                    model.removeFromFavorite(it)
                } else {
                    it.listOfLikes.add(AppUser.getPhone())
                    model.addToFavorite(it)
                }

            }
        })



        model.updateStatusMutableLiveData.observe(viewLifecycleOwner, {
            when (it) {
                UpdateStatus.LOADING -> {
                    Status.loading()
                }
                UpdateStatus.SUCCESS -> {
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                    Status.normal()

                }
                else -> {
                    Toast.makeText(requireActivity(), "Some Error ", Toast.LENGTH_SHORT).show()
                    Status.normal()
                }
            }
        })





        return bind.root

    }

}