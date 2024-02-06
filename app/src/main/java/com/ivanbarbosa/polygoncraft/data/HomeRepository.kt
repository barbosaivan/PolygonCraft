package com.ivanbarbosa.polygoncraft.data

import com.ivanbarbosa.polygoncraft.data.entities.Polygon
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
class HomeRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPolygons(): List<Polygon> =
        withContext(Dispatchers.IO) {
            apiService.getPolygons()
        }
}