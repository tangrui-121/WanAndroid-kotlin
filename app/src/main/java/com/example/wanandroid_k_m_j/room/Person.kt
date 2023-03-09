package com.example.wanandroid_k_m_j.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "my_person")
data class Person(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "person_name")
    val name: String
) {
    @Ignore
    val idonotneed: String = ""
}