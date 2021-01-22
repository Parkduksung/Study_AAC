package com.work.studyaac.lifecycle

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LifeCycleViewModel : ViewModel() {

    private val _dummyData1 = MutableLiveData<String>()
    val dummyData1: LiveData<String> = _dummyData1


    val dummyData2 = ObservableField<String>()

    init {
        _dummyData1.value = "hello"
    }


    fun changeDataValue() {
        _dummyData1.value = "change"
    }


}