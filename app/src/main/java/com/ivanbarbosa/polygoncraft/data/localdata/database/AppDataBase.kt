package com.ivanbarbosa.polygoncraft.data.localdata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivanbarbosa.polygoncraft.data.entities.PointEntity
import com.ivanbarbosa.polygoncraft.data.entities.PolygonEntity


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.localdata.database
* Create by Ivan Barbosa on 6/02/2024 at 8:10 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

@Database(version = 1, entities = [PolygonEntity::class, PointEntity::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun polygonDao(): PolygonDao
    abstract fun pointDAo(): PointDao
}