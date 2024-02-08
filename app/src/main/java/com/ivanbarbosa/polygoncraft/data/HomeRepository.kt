package com.ivanbarbosa.polygoncraft.data

import com.ivanbarbosa.polygoncraft.data.entities.Point
import com.ivanbarbosa.polygoncraft.data.entities.PointEntity
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.data.entities.PolygonEntity
import com.ivanbarbosa.polygoncraft.data.localdata.database.PointDao
import com.ivanbarbosa.polygoncraft.data.localdata.database.PolygonDao
import com.ivanbarbosa.polygoncraft.data.remotedata.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data
* Create by Ivan Barbosa on 6/02/2024 at 12:41 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val polygonDao: PolygonDao,
    private val pointDao: PointDao
) {
    suspend fun getPolygons(): List<Polygon> = withContext(Dispatchers.IO)
    {
        try {
            val retrofitPolygons = getRetrofit()
            insertPolygonWithPoints(retrofitPolygons)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext getAllPolygonsFromDatabase()
    }

    private suspend fun insertPolygonWithPoints(polygons: List<Polygon>) {
        polygons.forEach { polygon ->
            val existingPolygon = polygonDao.getPolygonByName(polygon.name)
            val polygonId: Long

            if (existingPolygon == null) {
                polygonId = polygonDao.insertPolygon(PolygonEntity(name = polygon.name))
                polygon.points.forEach { point ->
                    pointDao.insertPoint(
                        PointEntity(
                            x = point.x,
                            y = point.y,
                            polygonId = polygonId
                        )
                    )
                }
            }
        }
    }

    private suspend fun getRetrofit(): List<Polygon> {
        return apiService.getPolygons()
    }

    private fun getAllPolygonsFromDatabase(): List<Polygon> {
        val polygonsFromDatabase = polygonDao.getAllPolygons()
        val result = mutableListOf<Polygon>()

        for (polygonEntity in polygonsFromDatabase) {
            val pointsForPolygon = pointDao.getPointForPolygon(polygonEntity.id)
            val resultPoints = pointsForPolygon.map { pointEntity ->
                Point(pointEntity.x, pointEntity.y)
            }.toMutableList()
            result.add(Polygon(polygonEntity.name, resultPoints))
        }

        return result
    }
}