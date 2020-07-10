package com.work.studyaac.data.model

data class PagingItem(
    val answerId: Int,
    val contentLicense: String,
    val creationDate: Int,
    val isAccepted: Boolean,
    val lastActivityDate: Int,
    val lastEditDate: Int,
    val questionId: Int,
    val score: Int
)