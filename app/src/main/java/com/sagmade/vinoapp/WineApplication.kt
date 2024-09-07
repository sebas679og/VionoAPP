package com.sagmade.vinoapp

import android.app.Application
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class WineApplication : Application() {
    companion object {
        lateinit var database: WineDatabase
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE wineEntity ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        database = databaseBuilder(this,
            WineDatabase::class.java,
            "WineDatabase")
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}