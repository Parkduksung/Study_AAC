package com.work.studyaac.data.repository

import com.work.studyaac.data.model.Person

interface LifeCycleRepository {

    fun createPerson(
        person: Person
    )
}