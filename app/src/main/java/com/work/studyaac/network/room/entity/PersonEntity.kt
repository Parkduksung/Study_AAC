package com.work.studyaac.network.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.work.studyaac.data.model.Person

@Entity(tableName = "Person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val personNum: Int = 0,
    @ColumnInfo(name = "personName")
    val personName: String,
    @ColumnInfo(name = "personAge")
    val personAge: String
) {
    fun toPerson(): Person =
        Person().apply {
            personName = this@PersonEntity.personName
            personAge = this@PersonEntity.personAge
        }
}