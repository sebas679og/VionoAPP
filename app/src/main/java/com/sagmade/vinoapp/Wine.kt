package com.sagmade.vinoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wineEntity")
data class Wine( val winery: String,
                 val wine: String,
                 val rating: Rating,
                 val location: String,
                 val image: String,
                 @PrimaryKey val id: Int,
                 var isFavorite: Boolean = false)

/****
{
    "winery": "Maselva",
    "wine": "Emporda 2012",
    "rating": {
    "average": "4.9",
    "reviews": "88 ratings"
},
    "location": "Spain\n·\nEmpordà",
    "image": "https://images.vivino.com/thumbs/ApnIiXjcT5Kc33OHgNb9dA_375x500.jpg",
    "id": 1
}/
 ***/
