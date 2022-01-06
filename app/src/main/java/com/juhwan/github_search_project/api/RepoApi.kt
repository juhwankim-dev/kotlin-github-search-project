package com.juhwan.github_search_project.api

import com.juhwan.github_search_project.dto.RepoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {
    @GET("search/repositories")
    fun selectAllRepos(@Query("q") q: String, @Query("per_page") per_page: String, @Query("page") page: String): Call<RepoDto>
}