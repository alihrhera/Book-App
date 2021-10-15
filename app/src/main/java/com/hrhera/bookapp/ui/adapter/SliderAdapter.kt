package com.hrhera.bookapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso


class SliderAdapter : PagerAdapter() {
    private var list: List<String> = mutableListOf()
    fun setDataList(dataList: List<String>) {
        list = dataList
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image=ImageView(container.context)
        Picasso.get().load(list[position]).centerCrop().fit().into(image)
        container.addView(image)
        return image
    }

    override fun getCount(): Int = list.size

    override fun isViewFromObject(view: View, obje: Any): Boolean {
        return view == obje
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}