package com.juhwan.github_search_project.src

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.juhwan.github_search_project.R
import com.juhwan.github_search_project.config.BaseActivity
import com.juhwan.github_search_project.databinding.ActivityMainBinding
import com.juhwan.github_search_project.dto.RepoDto
import com.juhwan.github_search_project.repository.RepoRepository
import com.juhwan.github_search_project.util.RetrofitCallback
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager

private const val TAG = "MainActivity_juhwan"

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    fun initView() {
        val searchEditText = binding.svRepo.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))

        repoAdapter = RepoAdapter()
        binding.rvRepo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    fun initEvent() {
        binding.svRepo.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    RepoRepository.selectAllRepos(query ?: "", 10, 1, getRepoCallback())
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
                repoAdapter.concatList(responseData.items)
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