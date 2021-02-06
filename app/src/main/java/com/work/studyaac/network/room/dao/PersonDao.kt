package com.work.studyaac.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.work.studyaac.network.room.entity.PersonEntity

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerPerson(personEntity: PersonEntity)
}