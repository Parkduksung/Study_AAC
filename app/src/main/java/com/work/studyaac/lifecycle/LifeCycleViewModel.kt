package com.work.studyaac.lifecycle

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged

class LifeCycleViewModel : ViewModel() {

    private val _dummyData1 = MutableLiveData<String>()
    val dummyData1: LiveData<String> = _dummyData1


    private val _comparisionA = MutableLiveData("same")
    val comparisionA: LiveData<String> = _comparisionA


    private val _comparisionB = MutableLiveData("same")
    val comparisionB: LiveData<String> = _comparisionB.distinctUntilChanged()


    val dummyData2 = ObservableField<String>()

    init {
        _dummyData1.value = "hello"
    }


    fun changeDataValue() {
        _dummyData1.value = "change"
    }


    fun changeComparisionData() {
        _comparisionA.value = "same"
        _comparisionB.value = "same"
    }

}