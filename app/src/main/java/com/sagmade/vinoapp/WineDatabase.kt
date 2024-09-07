package com.sagmade.vinoapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wine::class], version = 2)
@TypeConverters(WineConverters::class)
abstract class WineDatabase : RoomDatabase() {
    abstract fun WineDao(): WineDao
}