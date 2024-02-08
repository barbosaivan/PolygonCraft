package com.ivanbarbosa.polygoncraft.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ivanbarbosa.polygoncraft.R
import com.ivanbarbosa.polygoncraft.data.PolygonRepository
import com.ivanbarbosa.polygoncraft.data.entities.Polygon
import com.ivanbarbosa.polygoncraft.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.home
* Create by Ivan Barbosa on 8/02/2024 at 3:47 a.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var repository: PolygonRepository

    @Mock
    private lateinit var observer: Observer<List<Polygon>>

    @Mock
    private lateinit var snackbarObserver: Observer<Int>

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(repository)

        requireNotNull(viewModel.getResult().hasObservers())
        requireNotNull(viewModel.getSnackbarMsg().hasObservers())

        viewModel.getResult().observeForever(observer)
        viewModel.getSnackbarMsg().observeForever(snackbarObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getPolygons success`() = testScope.runTest {
        val fakePolygons = FakeDataHome.generateFakePolygonsList(5)
        `when`(repository.getPolygons()).thenReturn(fakePolygons)

        viewModel.getPolygons()

        verify(observer).onChanged(fakePolygons)
        verify(snackbarObserver, Mockito.never()).onChanged(Mockito.anyInt())
    }

    @Test
    fun `test getPolygons empty result`() {
        testScope.runTest {
            `when`(repository.getPolygons()).thenReturn(emptyList())

            viewModel.getPolygons()

            verify(observer).onChanged(emptyList<Polygon>())
            verify(snackbarObserver).onChanged(R.string.main_error_empty_polygons)
        }
    }

    @Test
    fun `test getPolygons error`() {
        testScope.runTest {
            `when`(repository.getPolygons()).thenThrow(RuntimeException("Test exception"))

            viewModel.getPolygons()

            verify(snackbarObserver).onChanged(R.string.error_server)
        }
    }
}
