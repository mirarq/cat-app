package com.example.catapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats_table")
data class Cat(
    @PrimaryKey(autoGenerate = true) val catId: Int,
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "breeds")
    val breeds: String?,
    @ColumnInfo(name = "width")
    val width: Int?,
    @ColumnInfo(name = "height")
    val height: Int?,
)