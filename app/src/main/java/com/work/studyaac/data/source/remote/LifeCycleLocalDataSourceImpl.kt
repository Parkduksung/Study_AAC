package com.work.studyaac.data.source.remote

import android.util.Log
import com.work.studyaac.data.model.Person

class LifeCycleLocalDataSourceImpl : LifeCycleLocalDataSource {
    override fun createPerson(person: Person) {
        Log.d("결과", person.name.value ?: "")
        Log.d("결과", "여기찍힘")
    }
}