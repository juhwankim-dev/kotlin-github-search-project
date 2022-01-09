package com.juhwan.github_search_project.src

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.github_search_project.config.ApplicationClass.Companion.PER_PAGE
import com.juhwan.github_search_project.databinding.ListItemRepoBinding
import com.juhwan.github_search_project.dto.Item

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    var repoList = mutableListOf<Item?>()

    inner class RepoViewHolder(private val binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindInfo(item: Item?) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        var listItemBinding =
            ListItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindInfo(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun loadMorePage(list: List<Item>, page: Int) {
        // 2페이지 이상 불러올때는 먼저 ProgressBar Item을 삭제한다.
        if (page > 1) {
            repoList.removeAt((page - 1) * 10)
            notifyItemRemoved((page - 1) * 10)
        }

        // 새로운 페이지 Item들을 넣고
        repoList.addAll(list)

        // 새로운 Item들이 10개라면
        if (list.size == PER_PAGE) {
            // ProgressBar를 위치할 비어있는 Item을 넣어주고
            repoList.add(null)
            notifyItemRangeInserted((page - 1) * 10, PER_PAGE + 1)
        }

        // 새로운 Item들이 10개 미만 이라면
        else {
            notifyItemRangeInserted((page - 1) * 10, list.size)
        }
    }

    fun reset() {
        repoList.clear()
        notifyDataSetChanged()
    }
}