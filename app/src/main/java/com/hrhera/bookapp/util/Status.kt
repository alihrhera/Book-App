package com.hrhera.bookapp.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hrhera.bookapp.R


fun showHint(activity: FragmentActivity, title: String? = "", message: String? = "",onClickListener: DialogInterface.OnClickListener?) {
    AlertDialog.Builder(activity)
        .setTitle(title ?: "Hint")
        .setMessage(message ?: "Message")
        .setPositiveButton(android.R.string.ok, onClickListener)
        .show()
}


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