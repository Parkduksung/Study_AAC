package com.work.studyaac.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.work.studyaac.Injection.BASE_URL
import com.work.studyaac.network.api.PagingApi
import com.work.studyaac.network.model.paging.Item
import com.work.studyaac.network.model.paging.PagingResponse
import com.work.studyaac.util.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {

    private var pagingList: LiveData<PagedList<Item>>

    init {
        val pagingDataFactory =
            PagingDataSourceFactory(RetrofitInstance.getInstance(BASE_URL))

        val pagingListConfig =
            PagedList.Config.Builder().build()

        pagingList = LivePagedListBuilder(
            pagingDataFactory,
            pagingListConfig
        ).setFetchExecutor(Executors.newFixedThreadPool(5)).build()
    }

    fun getPaging(): LiveData<PagedList<Item>> =
        pagingList


    private class PagingDataSource(private val pagingApi: PagingApi) :
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

            Log.d("페이지결과-after", params.key.toString())
            pagingApi.getAnswers(params.key, PAGE_SIZE, SITE)
                .enqueue(object : Callback<PagingResponse> {
                    override fun onFailure(call: Call<PagingResponse>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<PagingResponse>,
                        response: Response<PagingResponse>
                    ) {
                        response.body()?.let {
                            Log.d("페이지결과-after", params.key.toString())
                            val adjacentKey = if (it.hasMore) params.key + 1 else null
                            Log.d("페이지결과-after", adjacentKey.toString())
                            if (adjacentKey != null) {
                                callback.onResult(it.items, adjacentKey)
                                Log.d("페이지결과-after", adjacentKey.toString())
                            }
                        }
                    }
                })
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
            Log.d("페이지결과-before", params.key.toString())
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

        companion object {
            private const val PAGE_SIZE = 50
            private const val FIRST_PAGE = 1
            private const val SITE = "stackoverflow"
        }
    }

    private class PagingDataSourceFactory(private val pagingApi: PagingApi) :
        DataSource.Factory<Int, Item>() {
        override fun create(): DataSource<Int, Item> {
            return PagingDataSource(pagingApi)
        }
    }

}