package com.example.dogedex.doglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogedex.Dog
import com.example.dogedex.api.ApiResponceStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DogListViewModel : ViewModel() {

    private val _dogList = MutableLiveData<List<Dog>>()
    val dogList : LiveData<List<Dog>>
            get() = _dogList

    private val _status = MutableLiveData<ApiResponceStatus<List<Dog>>>()
    val status : LiveData<ApiResponceStatus<List<Dog>>>
        get() = _status

    private val docRepository = DogRepository()
    init {
        downloadDogs()
    }

    private fun downloadDogs() {
        viewModelScope.launch {
                _status.value = ApiResponceStatus.Loading()
                handleResponceStatus(docRepository.downloadDogs())
        }
    }

    private fun handleResponceStatus(apiResponceStatus: ApiResponceStatus<List<Dog>>){
        if(apiResponceStatus is ApiResponceStatus.Success){
            _dogList.value = apiResponceStatus.data
        }
        _status.value = apiResponceStatus
    }
}