package com.juhwan.github_search_project.util

import com.juhwan.github_search_project.api.RepoApi
import com.juhwan.github_search_project.config.ApplicationClass

class RetrofitUtil {
    companion object {
        val repoService = ApplicationClass.retrofit.create(RepoApi::class.java)
    }
}