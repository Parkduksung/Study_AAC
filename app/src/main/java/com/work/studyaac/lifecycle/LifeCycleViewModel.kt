package com.work.studyaac.lifecycle

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.work.studyaac.data.model.Person
import com.work.studyaac.data.repository.LifeCycleRepository

class LifeCycleViewModel(private val lifeCycleRepository: LifeCycleRepository) : ViewModel(),
    LifecycleObserver {

    val personInfo by lazy {
        Person().apply {
            personName = ""
            personAge = ""
        }
    }
}
