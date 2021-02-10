package com.work.studyaac.data.repository

import com.work.studyaac.data.model.Person
import com.work.studyaac.data.source.remote.LifeCycleLocalDataSource
import com.work.studyaac.network.room.entity.PersonEntity
import kotlinx.coroutines.Job

class LifeCycleRepositoryImpl(private val lifeCycleLocalDataSource: LifeCycleLocalDataSource) :
    LifeCycleRepository {
    override fun createPerson(person: Person) {
        lifeCycleLocalDataSource.createPerson(person = person)
    }

    override fun getAllList(callback: (list: List<PersonEntity>) -> Unit) {
        lifeCycleLocalDataSource.getAllList(callback)
    }

}