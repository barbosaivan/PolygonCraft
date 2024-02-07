package com.ivanbarbosa.polygoncraft.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.entities
* Create by Ivan Barbosa on 6/02/2024 at 8:27 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

@Entity(
    tableName = "points",
    foreignKeys = [ForeignKey(
        entity = PolygonEntity::class,
        parentColumns = ["id"],
        childColumns = ["polygonId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("polygonId")]
)
data class PointEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val x: Double,
    val y: Double,
    val polygonId: Long
)
