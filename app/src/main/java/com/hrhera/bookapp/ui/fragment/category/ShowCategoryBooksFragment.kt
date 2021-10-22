package com.hrhera.bookapp.ui.fragment.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.callbacks.OnItemClick
import com.hrhera.bookapp.data.enums.AppStatus
import com.hrhera.bookapp.data.models.OneBook
import com.hrhera.bookapp.databinding.FragmentShowCategoryBookBinding
import com.hrhera.bookapp.ui.MainActivity
import com.hrhera.bookapp.ui.adapter.BookAdapter
import com.squareup.picasso.Picasso


class ShowCategoryBooksFragment : Fragment() {
    private var _binding: FragmentShowCategoryBookBinding? = null
    private val bind get() = _binding!!
    private lateinit var model: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowCategoryBookBinding.inflate(inflater)
        model = (requireActivity() as MainActivity).categoryViewModel
        model.singleCategoryMutableLiveData.observe(viewLifecycleOwner, {
            Picasso.get().load(it.photo).fit().centerCrop()
                .error(R.drawable.book_category_place_holder)
                .into(bind.categoryImage)
            bind.categoryName.text = it.name
        })
        bind.dataListView.layoutManager = GridLayoutManager(requireContext(), 3)
        val bookAdapter = BookAdapter()
        bind.dataListView.adapter = bookAdapter
        model.booksOfCategoryLiveData().observe(viewLifecycleOwner, {
            if (it["status"] == AppStatus.LOADING) {
                bind.bookLoader.visibility = View.VISIBLE
                return@observe
            }
            bind.bookLoader.visibility = View.GONE

            if (!(it["error"] as Boolean)) {
                val mutableList = mutableListOf<OneBook>()
                if (it["data"] is List<*>)
                    for (i in it["data"] as List<*>) {
                        if (i is OneBook) {
                            mutableList.add(i)
                        }
                    }
                bookAdapter.apply {
                    submitList(mutableList)
                    onItemClick = object : OnItemClick {
                        override fun onClick(item: Any) {
                            (requireActivity() as MainActivity).bookViewModel.setSingleBook(item as OneBook)
                            (requireActivity() as MainActivity).navController.navigate(R.id.showBookFragment)
                        }
                    }
                }
            }
        })

        return bind.root

    }

}