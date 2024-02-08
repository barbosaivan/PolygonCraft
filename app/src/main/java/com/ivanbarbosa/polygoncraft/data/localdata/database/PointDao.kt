package com.ivanbarbosa.polygoncraft.data.localdata.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivanbarbosa.polygoncraft.data.entities.PointEntity


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.localdata.database
* Create by Ivan Barbosa on 6/02/2024 at 8:04 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

@Dao
interface PointDao {
    @Insert
    suspend fun insertPoint(point: PointEntity)

    @Query("SELECT * FROM points WHERE polygonId = :polygonId")
    fun getPointForPolygon(polygonId: Long): MutableList<PointEntity>
}