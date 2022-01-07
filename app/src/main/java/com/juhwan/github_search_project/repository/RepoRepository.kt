package com.juhwan.github_search_project.repository

import android.util.Log
import com.juhwan.github_search_project.config.ApplicationClass.Companion.PER_PAGE
import com.juhwan.github_search_project.dto.RepoDto
import com.juhwan.github_search_project.util.RetrofitCallback
import com.juhwan.github_search_project.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepoRepository {
    fun selectAllRepos(q: String, page: Int, callback: RetrofitCallback<RepoDto>) {
        RetrofitUtil.repoService.selectAllRepos(q, PER_PAGE.toString(), page.toString()).enqueue(object: Callback<RepoDto> {
            override fun onResponse(
                call: Call<RepoDto>,
                response: Response<RepoDto>
            ) {
                if (response.code() == 200) {
                    callback.onSuccess(response.code(), response.body()!!)
                } else {
                    callback.onError(response.code())
                }
            }

            override fun onFailure(call: Call<RepoDto>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}