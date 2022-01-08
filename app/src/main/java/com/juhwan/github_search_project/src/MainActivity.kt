package com.juhwan.github_search_project.src

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import com.juhwan.github_search_project.R
import com.juhwan.github_search_project.config.BaseActivity
import com.juhwan.github_search_project.databinding.ActivityMainBinding
import com.juhwan.github_search_project.dto.RepoDto
import com.juhwan.github_search_project.repository.RepoRepository
import com.juhwan.github_search_project.util.RetrofitCallback
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

private const val TAG = "싸피"
private var page = 1

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var repoAdapter: RepoAdapter
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initEvent()
    }

    private fun initView() {
        val searchEditText = binding.svRepo.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(resources.getColor(R.color.white))
        searchEditText.setHintTextColor(resources.getColor(R.color.white))

        repoAdapter = RepoAdapter()
        binding.rvRepo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    private fun initEvent() {
        binding.svRepo.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.lottieGithub.visibility = View.VISIBLE
                    this@MainActivity.query = query ?: ""
                    page = 1
                    repoAdapter.reset()
                    selectAllRepos()
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        binding.rvRepo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount

                if (lastVisibleItemPosition > 0 &&
                    !binding.rvRepo.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount - 1) {
                    selectAllRepos()
                }
            }
        })
    }

    private fun selectAllRepos() {
        RepoRepository.selectAllRepos(query, page, RepoCallback())
    }

    inner class RepoCallback: RetrofitCallback<RepoDto> {
        override fun onSuccess(
            code: Int,
            responseData: RepoDto
        ) {
            if(page == 1) {
                binding.lottieGithub.visibility = View.GONE
            }

            if(responseData != null) {
                if(page == 1 && responseData.items.size == 0) {
                    showToastMessage("검색결과가 없습니다")
                }

                repoAdapter.loadMorePage(responseData.items, page++)
                Log.d(TAG, "onSuccess: ${responseData.items.size} repositories received")
            } else {
                Log.d(TAG, "onSuccess: responseData is null")
            }
        }

        override fun onFailure(t: Throwable) {
            if(page == 1) {
                binding.lottieGithub.visibility = View.GONE
            }
            showToastMessage("네트워크 연결을 확인해주세요")
            Log.d(TAG, t.message ?: "onFailure")
        }

        override fun onError(code: Int) {
            if(page == 1) {
                binding.lottieGithub.visibility = View.GONE
            }
            
            showToastMessage("서버가 불안정합니다. 나중에 다시 시도해주세요")
            Log.d(TAG, "onError: Error Code $code")
        }
    }
}