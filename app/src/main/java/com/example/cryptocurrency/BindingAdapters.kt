package com.example.cryptocurrency

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("rank")
fun bindTextViewToDisplayRank(textView: TextView, number: Int) {
    textView.text = number.toString()
}