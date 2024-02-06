package com.ivanbarbosa.polygoncraft.data.remotedata

import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.utils.Constants
import retrofit2.http.*


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.remote
* Create by Ivan Barbosa on 6/02/2024 at 12:27 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
fun interface ApiService {
    @GET(Constants.METHOD_PARAM)
    suspend fun getPolygons(): List<Polygon>
}