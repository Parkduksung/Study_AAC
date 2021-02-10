package com.work.studyaac.network.room.dao

import android.app.Person
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.work.studyaac.network.room.entity.PersonEntity

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerPerson(personEntity: PersonEntity)

    @Query("SELECT * FROM person")
    fun getAllList(): List<PersonEntity>

}