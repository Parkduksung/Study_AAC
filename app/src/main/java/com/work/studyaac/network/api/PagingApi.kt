package com.work.studyaac.network.api

import com.work.studyaac.network.model.paging.PagingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PagingApi {
    @GET("answers")
    fun getAnswers(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("site") site: String
    ): Call<PagingResponse>
}