package com.mostafamnaguib.newsapp.utils

import android.content.res.Resources
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

fun viewSnackBar(viewBinder: View, resources: Resources, msg: String){

    val snack: Snackbar = Snackbar.make(viewBinder, msg, Snackbar.LENGTH_LONG)
        .setAction("CLOSE") { }
        .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
    val view = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    params.setMargins(params.leftMargin,
        params.topMargin + 200,
        params.rightMargin,
        params.bottomMargin);
    view.layoutParams = params
    snack.show()

}
