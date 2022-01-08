package com.juhwan.github_search_project

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.juhwan.github_search_project.config.ApplicationClass
import com.juhwan.github_search_project.util.RetrofitUtil

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test(timeout = 5000)
    fun repoApiTimeoutTest() {
        RetrofitUtil.repoService.selectAllRepos("java", ApplicationClass.PER_PAGE.toString(), "1").execute()
    }

    @Test
    fun responseDataTest1() {
        val EXPECTED_SIZE = 10

        var response = RetrofitUtil.repoService.selectAllRepos("kotlin", ApplicationClass.PER_PAGE.toString(), "1").execute()
        var responseData = response.body()!!
        assertEquals(EXPECTED_SIZE, responseData.items.size)
    }

    @Test
    fun responseDataTest2() {
        val EXPECTED_SIZE = 1

        var response = RetrofitUtil.repoService.selectAllRepos("ssaign", ApplicationClass.PER_PAGE.toString(), "1").execute()
        var responseData = response.body()!!
        assertEquals(EXPECTED_SIZE, responseData.items.size)
    }

    @Test
    fun responseDataTest3() {
        val EXPECTED_SIZE = 0

        var response = RetrofitUtil.repoService.selectAllRepos("weocimrpcjioe_dummy_query", ApplicationClass.PER_PAGE.toString(), "1").execute()
        var responseData = response.body()!!
        assertEquals(EXPECTED_SIZE, responseData.items.size)
    }
}