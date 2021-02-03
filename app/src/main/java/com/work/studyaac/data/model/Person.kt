package com.work.studyaac.data.model

import androidx.lifecycle.MutableLiveData

data class Person(
    val name: MutableLiveData<String>,
    val age: MutableLiveData<String>
)