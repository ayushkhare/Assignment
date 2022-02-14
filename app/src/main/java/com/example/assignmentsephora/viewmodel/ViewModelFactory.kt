package com.example.assignmentsephora.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentsephora.di.DependencyUtil
import com.example.assignmentsephora.service.SephoraRepository

class ViewModelFactory() : ViewModelProvider.Factory {
    lateinit var sephoraRepository: SephoraRepository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        sephoraRepository = DependencyUtil.provideSephoraRepository()
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(sephoraRepository) as T
        } else {
            throw IllegalStateException("ViewModel not found")
        }
    }
}