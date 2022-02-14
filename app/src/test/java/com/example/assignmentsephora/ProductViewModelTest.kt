package com.example.assignmentsephora

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignmentsephora.service.SephoraRepository
import com.example.assignmentsephora.viewmodel.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesDispatcherRule = CoroutineDispatcherRule()

    private lateinit var viewModel: ProductViewModel

    @Test
    fun testGetProductResponse() = run {
        viewModel = ProductViewModel(SephoraRepository(MockSephoraService()))
        viewModel.getProductResponse(1)
        val mockResponse = testResponse
        val expectedSize = viewModel.product.value?.productList?.size
        val actualSize = mockResponse.productList.size
        Assert.assertEquals(expectedSize, actualSize)
    }
}