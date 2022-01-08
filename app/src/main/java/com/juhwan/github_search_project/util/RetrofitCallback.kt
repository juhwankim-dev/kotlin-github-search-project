package com.juhwan.github_search_project.util

interface RetrofitCallback<T> {
    fun onError(code: Int)

    fun onSuccess(code: Int, responseData: T)

    fun onFailure(t: Throwable)
}