package com.work.studyaac.data.source.remote

import com.work.studyaac.data.model.Person

interface LifeCycleLocalDataSource {
    fun createPerson(
        person: Person
    )
}