package com.work.studyaac.network.model.paging


import com.google.gson.annotations.SerializedName

data class PagingResponse(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)