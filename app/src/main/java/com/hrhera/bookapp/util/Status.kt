package com.hrhera.bookapp.util

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.hrhera.bookapp.R

object Status {
    private var loading: Dialog? = null
    fun init(activity: AppCompatActivity) {
        loading = object : Dialog(activity) {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.dialog_loading)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }
    }


    fun loading() {
        if (null != loading) {
            if (!(loading!!.isShowing)) {
                loading!!.show()
            }
        }
    }

    fun normal() {
        if (null != loading) {
            if ((loading!!.isShowing)) {
                loading!!.dismiss()
            }
        }
    }

}