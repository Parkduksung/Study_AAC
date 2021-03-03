package com.work.studyaac.lifecycle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class LifeCycleSaveInstanceViewModel(private val handle: SavedStateHandle) : ViewModel() {

    private val COUNT_KEY = "count"

    // Get value of SavedStateHandle
    private var counter = handle.get<Int>(COUNT_KEY) ?: 0
        set(value) {
            // Set value of SavedStateHandle
            handle[COUNT_KEY] = value
            field = value
        }

    // Get LiveData of SavedStateHandle
    val countLiveData: LiveData<Int> = handle.getLiveData(COUNT_KEY, 0)

    // 카운트 증가
    fun incCounter() {
        ++counter
    }

    override fun onCleared() {
        Log.d("결과", "LifeCycleSaveInstanceViewModel onCleared")
        super.onCleared()
    }
}