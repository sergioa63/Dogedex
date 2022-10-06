package com.example.dogedex.api

import com.example.dogedex.R
import com.example.dogedex.api.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(call: suspend  () -> T) : ApiResponceStatus<T>{
    return withContext(Dispatchers.IO) {
        try {
            ApiResponceStatus.Success(call())
        } catch (e : UnknownHostException) {
            ApiResponceStatus.Error(R.string.unknown_host_exception_error)
        } catch (e : Exception ) {
            ApiResponceStatus.Error(R.string.unknown_error)
        }
    }
}