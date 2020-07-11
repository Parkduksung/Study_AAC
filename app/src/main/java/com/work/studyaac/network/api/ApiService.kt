package com.work.studyaac.network.api

import com.work.studyaac.App
import com.work.studyaac.network.model.paging.BaseResponse
import com.work.studyaac.network.model.paging.NewsFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getNews(
        @Query("q") name: String,
        @Query("page") user: Int
    ): Call<BaseResponse<List<NewsFeed>>>

    companion object {
        fun create(): ApiService {
            return App.instance.retorfit.create(ApiService::class.java)
        }
    }
}