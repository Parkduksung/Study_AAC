package com.work.studyaac.data.repository

import com.work.studyaac.data.model.Person
import com.work.studyaac.network.room.entity.PersonEntity

interface LifeCycleRepository {
    fun createPerson(
        person: Person
    )

    fun getAllList(
        callback: (list : List<PersonEntity>) -> Unit
    )
}