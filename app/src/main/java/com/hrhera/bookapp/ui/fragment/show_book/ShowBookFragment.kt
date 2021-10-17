package com.hrhera.bookapp.ui.fragment.show_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.AppComment
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.databinding.FragmentBookInfoBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.adapter.CommentsAdapter
import com.hrhera.bookapp.util.Status
import com.squareup.picasso.Picasso
import java.util.*


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
            Picasso.get().load(it.photo).fit().centerCrop().into(bind.bookImageView)
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



            bind.bookComments.layoutManager = LinearLayoutManager(requireContext())
            val commentAdapter = CommentsAdapter()
            bind.bookComments.adapter = commentAdapter
            commentAdapter.onSendClick = object : OnItemClick {
                override fun onClick(item: Any) {
                    val comment = AppComment(
                        _id = "${Calendar.getInstance().timeInMillis}",
                        user = AppUser.getUser(),
                        comment = item as String
                    )
                    it.commentList.add(comment)
                    model.updateBook(it)
                }
            }
            val list= mutableListOf<AppComment>()
            list.addAll(it.commentList)
            list.add(AppComment(id = -5))
            commentAdapter.submitList(list)

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