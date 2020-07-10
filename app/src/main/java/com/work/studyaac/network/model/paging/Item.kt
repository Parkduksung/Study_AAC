package com.work.studyaac.network.model.paging


import com.google.gson.annotations.SerializedName
import com.work.studyaac.data.model.PagingItem

data class Item(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("content_license")
    val contentLicense: String,
    @SerializedName("creation_date")
    val creationDate: Int,
    @SerializedName("is_accepted")
    val isAccepted: Boolean,
    @SerializedName("last_activity_date")
    val lastActivityDate: Int,
    @SerializedName("last_edit_date")
    val lastEditDate: Int,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("question_id")
    val questionId: Int,
    @SerializedName("score")
    val score: Int
) {
    fun toPagingItem(): PagingItem =
        PagingItem(
            answerId,
            contentLicense,
            creationDate,
            isAccepted,
            lastActivityDate,
            lastEditDate,
            questionId,
            score
        )
}
