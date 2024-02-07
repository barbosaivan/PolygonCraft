package com.ivanbarbosa.polygoncraft.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.entities
* Create by Ivan Barbosa on 6/02/2024 at 8:28 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

@Entity(tableName = "polygons", indices = [Index(value = ["name"], unique = true)])
data class PolygonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)