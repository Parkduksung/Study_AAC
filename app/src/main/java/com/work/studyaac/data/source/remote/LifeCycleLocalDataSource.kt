package com.work.studyaac.data.source.remote

import com.work.studyaac.data.model.Person
import com.work.studyaac.network.room.entity.PersonEntity

interface LifeCycleLocalDataSource {
    fun createPerson(
        person: Person
    )

    fun getAllList(
        callback: (list: List<PersonEntity>)-> Unit
    )
}