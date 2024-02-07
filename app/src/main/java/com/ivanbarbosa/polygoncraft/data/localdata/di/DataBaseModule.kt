package com.ivanbarbosa.polygoncraft.data.localdata.di

import android.content.Context
import androidx.room.Room
import com.ivanbarbosa.polygoncraft.data.localdata.database.AppDataBase
import com.ivanbarbosa.polygoncraft.data.localdata.database.PointDao
import com.ivanbarbosa.polygoncraft.data.localdata.database.PolygonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.localdata.di
* Create by Ivan Barbosa on 6/02/2024 at 10:23 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun providePolygonDao(appDatabase: AppDataBase): PolygonDao {
        return appDatabase.polygonDao()
    }

    @Provides
    fun providePointDao(appDatabase: AppDataBase): PointDao {
        return appDatabase.pointDAo()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "polygoncraft_database"
        ).build()
    }
}
