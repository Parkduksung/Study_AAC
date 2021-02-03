package com.work.studyaac.data.repository

import com.work.studyaac.data.model.Person
import com.work.studyaac.data.source.remote.LifeCycleLocalDataSource

class LifeCycleRepositoryImpl(private val lifeCycleLocalDataSource: LifeCycleLocalDataSource) :
    LifeCycleRepository {
    override fun createPerson(person: Person) {
        lifeCycleLocalDataSource.createPerson(person = person)
    }
}