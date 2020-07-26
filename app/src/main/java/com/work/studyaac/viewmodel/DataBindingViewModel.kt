package com.work.studyaac.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel : ViewModel() {

    private val _name = MutableLiveData("duksung")
    val name: LiveData<String> get() = _name

    private val _lastName = MutableLiveData("park")
    val lastName: LiveData<String> get() = _lastName

    private val _likes = MutableLiveData(0)
    val likes: LiveData<Int> get() = _likes

    val textForToWay = MutableLiveData<String>()

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }

}