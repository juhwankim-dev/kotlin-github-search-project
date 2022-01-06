package com.juhwan.github_search_project.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.juhwan.github_search_project.R
import com.juhwan.github_search_project.config.BaseActivity
import com.juhwan.github_search_project.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView();
    }

    fun initView() {

    }
}