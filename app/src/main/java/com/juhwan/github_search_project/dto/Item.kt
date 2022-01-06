package com.juhwan.github_search_project.dto

data class Item(
    val full_name: String,
    val language: String,
    val owner: Owner
)