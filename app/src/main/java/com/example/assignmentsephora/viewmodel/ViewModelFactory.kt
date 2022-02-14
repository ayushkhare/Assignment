package com.example.assignmentsephora.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentsephora.service.SephoraRepository

class ViewModelFactory(private val repository: SephoraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(repository) as T
        } else {
            throw IllegalStateException("ViewModel not found")
        }
    }
}