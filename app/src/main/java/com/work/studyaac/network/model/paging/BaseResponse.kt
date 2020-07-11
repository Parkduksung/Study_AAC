package com.work.studyaac.network.model.paging

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("articles")
    val articles: T?,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)