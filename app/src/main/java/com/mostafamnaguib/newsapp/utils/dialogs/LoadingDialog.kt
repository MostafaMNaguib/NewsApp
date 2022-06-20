package com.mostafamnaguib.newsapp.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import com.mostafamnaguib.newsapp.databinding.DialogLoadingBinding

class LoadingDialog(private val mContext: Context) : Dialog(mContext)
{

    private lateinit var binding: DialogLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DialogLoadingBinding.inflate(LayoutInflater.from(mContext))

        setContentView(binding.root)
        setUp()

    }

    private fun setUp() {

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setCancelable(false)

        //        val wlp = dialog!!.window!!.attributes
        //        wlp.gravity = Gravity.BOTTOM
        //        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        //        dialog!!.window!!.attributes = wlp

    }

}