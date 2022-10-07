package com.example.dogedex.api

import com.example.dogedex.R
import com.example.dogedex.api.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

private const val UNAUTHORIZED_ERROR_CODE = 401

suspend fun <T> makeNetworkCall(call: suspend  () -> T) : ApiResponceStatus<T>{
    return withContext(Dispatchers.IO) {
        try {
            ApiResponceStatus.Success(call())
        } catch (e : UnknownHostException) {
            ApiResponceStatus.Error(R.string.unknown_host_exception_error)
        } catch (e : HttpException){
            val errorMessage = if( e.code() == UNAUTHORIZED_ERROR_CODE){
                R.string.wrong_user_or_password
            } else {
                R.string.unknown_error
            }
            ApiResponceStatus.Error(errorMessage)
        } catch (e : Exception ) {
            val errorMessage = when(e.message) {
                "sign_up_error" -> R.string.error_sign_up
                "sign_in_error" -> R.string.error_sign_in
                "user_already_exists" -> R.string.user_already_exists
                "error_adding_dog" -> R.string.error_adding_dog
                else -> R.string.unknown_error
            }
            ApiResponceStatus.Error(errorMessage)
        }
    }
}