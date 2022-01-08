package com.juhwan.github_search_project.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageByGlide")
    fun setImageByGlide(view: ImageView, url: String){
        if(url.isEmpty()) {
            Glide.with(view.context)
                .load("https://user-images.githubusercontent.com/76620764/148633216-6f17ddc8-9f1e-4666-b4a0-2c41e01a7314.png")
                .into(view)
        } else {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}