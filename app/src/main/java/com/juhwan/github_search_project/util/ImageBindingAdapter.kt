package com.juhwan.github_search_project.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageByGlide")
    fun setImageByGlide(view: ImageView, url: String){
        Glide.with(view.context)
            .load(url)
            .override(80, 80)
            .into(view)
    }
}