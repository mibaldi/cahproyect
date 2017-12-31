package com.mibaldi.cah.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mibaldi.cah.ui.viewModels.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val key = "MainViewModel"
            if(hashMapViewModel.containsKey(key)){
                return getViewModel(key) as T
            } else {
                addViewModel(key, MainViewModel())
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        val hashMapViewModel = HashMap<String, ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel){
            hashMapViewModel.put(key, viewModel)
        }
        fun getViewModel(key: String): ViewModel? {
            return hashMapViewModel[key]
        }
    }
}