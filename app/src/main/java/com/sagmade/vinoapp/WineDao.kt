package com.sagmade.vinoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WineDao {
    @Query("SELECT * FROM wineEntity")
    fun getAllWines(): MutableList<Wine>

    @Query("SELECT * FROM wineEntity WHERE isFavorite == :isFavorite ")
    fun getWinesFav(isFavorite: Boolean) : MutableList<Wine>

    @Insert
    fun addWine(wine: Wine): Long

    @Update
    fun updateWine(wine: Wine): Int

    @Delete
    fun deleteWine(wine: Wine): Int


}