package com.example.cryptocurrency

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("rank")
fun bindTextViewToDisplayRank(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("isActive")
fun bindtextview(textView: TextView, iSActive: Boolean) {
    if (iSActive) {
        textView.setText("isActive")
    } else {
        textView.setText("NotActive")

    }
}
@BindingAdapter("isActiveColor")
fun bindColortextview(textViewc: TextView, iSActive: Boolean) {
    if (iSActive) {
        textViewc.setTextColor(Color.GREEN)
    } else {
        textViewc.setTextColor(Color.RED)

    }
}