package com.ivanbarbosa.polygoncraft.desing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ivanbarbosa.polygoncraft.data.PolygonRepository
import com.ivanbarbosa.polygoncraft.ui.desing.DesignViewModel
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
import org.mockito.MockitoAnnotations


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.desing
* Create by Ivan Barbosa on 8/02/2024 at 8:12 a. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@ExperimentalCoroutinesApi
class DesignViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var repository: PolygonRepository

    @Mock
    private lateinit var observer: Observer<Boolean>

    private lateinit var viewModel: DesignViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = DesignViewModel(repository)
        viewModel.polygonSaved.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test savePolygon success`() = testScope.runTest {
        val fakePolygon = FakeDataDesign.generateFakePolygons()
        Mockito.`when`(repository.savePolygonWithPoints(fakePolygon)).thenReturn(true)

        viewModel.savePolygon(fakePolygon)

        verify(repository).savePolygonWithPoints(fakePolygon)
        verify(observer).onChanged(true)
    }

    @Test
    fun `test savePolygon failure`() = testScope.runTest {
        val fakePolygon = FakeDataDesign.generateFakePolygons()
        val errorMessage = "Test exception"
        Mockito.`when`(repository.savePolygonWithPoints(fakePolygon))
            .thenThrow(RuntimeException(errorMessage))

        viewModel.savePolygon(fakePolygon)

        verify(observer).onChanged(false)
    }
}
