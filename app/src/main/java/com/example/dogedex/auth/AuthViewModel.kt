package com.example.dogedex.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogedex.api.ApiResponceStatus
import com.example.dogedex.model.User
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    private val _status = MutableLiveData<ApiResponceStatus<User>>()
    val status : LiveData<ApiResponceStatus<User>>
        get() = _status

    private val authRepository : AuthRepository = AuthRepository()
    fun singUp (email: String, password : String, passwordConfirmation : String) {
        _status.value = ApiResponceStatus.Loading()
       viewModelScope.launch {
           handleResponceStatus(authRepository.singUp(email, password, passwordConfirmation))
        }
    }

    fun login(email: String, password: String) {
        _status.value = ApiResponceStatus.Loading()
        viewModelScope.launch {
            handleResponceStatus(authRepository.login(email, password))
        }
    }

    private fun handleResponceStatus(apiResponceStatus: ApiResponceStatus<User>){
        if(apiResponceStatus is ApiResponceStatus.Success){
            _user.value = apiResponceStatus.data
        }
        _status.value = apiResponceStatus
    }

}