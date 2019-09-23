package com.lambdaschool.choretracker.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun openSoftKeyboard(context: Context?, view: View) {
    view.requestFocus()
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}