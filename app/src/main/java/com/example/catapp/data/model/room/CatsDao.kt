package com.example.catapp.data.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catapp.data.model.Cat

@Dao
interface CatsDao {
    @Insert
    suspend fun insertCat(cat: Cat)

    @Query("SELECT * FROM cats_table")
    fun getFavoriteCats(): List<Cat>
    @Delete
    suspend fun deleteCat(cat: Cat)
}