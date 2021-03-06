package com.work.studyaac.network.model.paging

import com.google.gson.annotations.SerializedName

data class NewsFeed(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: Any?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
) {
    data class Source(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?
    )
}