package com.work.studyaac.data.source.remote

import androidx.paging.PageKeyedDataSource
import com.work.studyaac.network.api.PagingApi
import com.work.studyaac.network.model.paging.Item
import com.work.studyaac.network.model.paging.PagingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagingRemoteDataSourceImpl(private val pagingApi: PagingApi) : PagingRemoteDataSource,
    PageKeyedDataSource<Int, Item>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        pagingApi.getAnswers(FIRST_PAGE, PAGE_SIZE, SITE)
            .enqueue(object : Callback<PagingResponse> {
                override fun onFailure(call: Call<PagingResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<PagingResponse>,
                    response: Response<PagingResponse>
                ) {
                    response.body()?.let {
                        callback.onResult(it.items, null, FIRST_PAGE + 1)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        pagingApi.getAnswers(params.key, PAGE_SIZE, SITE)
            .enqueue(object : Callback<PagingResponse> {
                override fun onFailure(call: Call<PagingResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<PagingResponse>,
                    response: Response<PagingResponse>
                ) {
                    val adjacentKey = if (params.key > 1) params.key + 1 else null

                    response.body()?.let {
                        callback.onResult(it.items, adjacentKey)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        pagingApi.getAnswers(params.key, PAGE_SIZE, SITE)
            .enqueue(object : Callback<PagingResponse> {
                override fun onFailure(call: Call<PagingResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<PagingResponse>,
                    response: Response<PagingResponse>
                ) {
                    val adjacentKey = if (params.key > 1) params.key - 1 else null

                    response.body()?.let {
                        callback.onResult(it.items, adjacentKey)
                    }
                }
            })
    }

    override fun getPagingData(
        page: Int,
        pageSize: Int,
        site: String,
        onSuccess: (response: PagingResponse) -> Unit,
        onFailure: () -> Unit
    ) {

        pagingApi.getAnswers(page, pageSize, SITE).enqueue(object : Callback<PagingResponse> {
            override fun onFailure(call: Call<PagingResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<PagingResponse>,
                response: Response<PagingResponse>
            ) {
                response.body()?.let(onSuccess)
            }
        })
    }


    companion object {

        private var instance: PagingRemoteDataSourceImpl? = null

        fun getInstance(pagingApi: PagingApi): PagingRemoteDataSourceImpl =
            instance ?: PagingRemoteDataSourceImpl(pagingApi).also {
                instance = it
            }

        private const val PAGE_SIZE = 10
        private const val FIRST_PAGE = 1
        private const val SITE = "stackoverflow"
    }

}