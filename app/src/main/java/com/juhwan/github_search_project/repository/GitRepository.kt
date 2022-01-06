package com.juhwan.github_search_project.repository

import android.util.Log
import com.juhwan.github_search_project.dto.RepoDto
import com.juhwan.github_search_project.util.RetrofitCallback
import com.juhwan.github_search_project.util.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GitRepository {
    fun selectAllRepos(q: String, per_page: Int, page: Int, callback: RetrofitCallback<RepoDto>) {
        RetrofitUtil.repoService.selectAllRepos(q, per_page.toString(), page.toString()).enqueue(object: Callback<RepoDto> {
            override fun onResponse(
                call: Call<RepoDto>,
                response: Response<RepoDto>
            ) {
                if (response.isSuccessful) {
                    try {
                        callback.onSuccess(response.code(), response.body()!!)
                    } catch (e: Exception){
                        callback.onFailure(response.code())
                    }
                }
            }

            override fun onFailure(call: Call<RepoDto>, t: Throwable) {
                callback.onError(t)
            }
        })
    }
}