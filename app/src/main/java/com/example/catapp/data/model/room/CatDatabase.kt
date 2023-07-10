package com.example.catapp.data.model.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catapp.data.model.Cat

@Database(entities = [Cat::class], version = 1)
abstract class CatDatabase: RoomDatabase() {
    abstract fun dao(): CatsDao
    companion object {
        private var INSTANCE: CatDatabase? = null
        fun getInstance(context: Context): CatDatabase?{
            if(INSTANCE == null){
                synchronized(CatDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    CatDatabase::class.java,"cat.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}