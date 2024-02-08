package com.ivanbarbosa.polygoncraft.ui.desing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanbarbosa.polygoncraft.data.PolygonRepository
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.desing
* Create by Ivan Barbosa on 8/02/2024 at 1:47 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@HiltViewModel
class DesignViewModel @Inject constructor(private val repository: PolygonRepository) : ViewModel() {
    private val resultPolygonSaved = MutableLiveData<Boolean>()
    val polygonSaved: LiveData<Boolean> get() = resultPolygonSaved

    fun savePolygon(polygon: Polygon) {
        viewModelScope.launch {
            try {
                val result = repository.savePolygonWithPoints(polygon)
                resultPolygonSaved.value = result
            } catch (e: Exception) {
                resultPolygonSaved.value = false
            }
        }
    }
}