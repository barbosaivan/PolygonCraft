package com.ivanbarbosa.polygoncraft.data.localdata.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ivanbarbosa.polygoncraft.data.entities.PolygonEntity


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.localdata.database
* Create by Ivan Barbosa on 6/02/2024 at 8:03 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@Dao
interface PolygonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPolygon(polygon: PolygonEntity): Long

    @Transaction
    @Query("SELECT * FROM polygons")
    suspend fun getAllPolygons(): List<PolygonEntity>

    @Query("SELECT * FROM polygons WHERE name = :name LIMIT 1")
    suspend fun getPolygonByName(name: String): PolygonEntity?
}

