package com.juhwan.github_search_project.src

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.juhwan.github_search_project.R
import com.juhwan.github_search_project.config.BaseActivity
import com.juhwan.github_search_project.databinding.ActivityMainBinding
import com.juhwan.github_search_project.dto.RepoDto
import com.juhwan.github_search_project.repository.GitRepository
import com.juhwan.github_search_project.util.RetrofitCallback
import android.widget.EditText

private const val TAG = "MainActivity_juhwan"

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    fun initView() {
        val searchEditText = binding.svGit.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))
    }

    fun initEvent() {
        binding.svGit.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    GitRepository.selectAllRepos(query ?: "", 10, 1, getRepoCallback())
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }

    inner class getRepoCallback: RetrofitCallback<RepoDto> {
        override fun onSuccess(
            code: Int,
            responseData: RepoDto
        ) {
            if(responseData != null) {
                showToastMessage("${responseData.items.size}개의 검색결과")
                Log.d(TAG, "onSuccess: ${responseData.items.size} repositories received")
            } else {
                showToastMessage("검색결과가 없습니다")
                Log.d(TAG, "onSuccess: no responseData")
            }
        }

        override fun onError(t: Throwable) {
            Log.d(TAG, t.message ?: "onError")
        }

        override fun onFailure(code: Int) {
            Log.d(TAG, "onFailure: Error Code $code")
        }
    }
}