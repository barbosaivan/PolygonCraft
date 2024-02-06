package com.ivanbarbosa.polygoncraft.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.HomeRepository
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.ui.home
* Create by Ivan Barbosa on 6/02/2024 at 12:17 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val result = MutableLiveData<List<Polygon>>()
    private val snackbarMsg = MutableLiveData<Int>()
    private val loaded = MutableLiveData<Boolean>()

    fun getResult(): MutableLiveData<List<Polygon>> = result

    fun getSnackbarMsg() = snackbarMsg

    fun isLoaded() = loaded

    fun getPolygons() {
        viewModelScope.launch {
            try {
                loaded.value = false
                val result = repository.getPolygons()
                this@HomeViewModel.result.value = result
                if (result.isEmpty()) {
                    snackbarMsg.value = R.string.main_error_empty_polygons
                }
            } catch (e: Exception) {
                snackbarMsg.value = R.string.error_server
            } finally {
                loaded.value = true
            }
        }
    }
}