package com.example.dogedex.api

import com.example.dogedex.Dog

sealed class ApiResponceStatus<T> (){
    class Success<T>(val data : T) : ApiResponceStatus<T>()
    class Error<T>(val msn : Int) : ApiResponceStatus<T>()
    class Loading<T>() : ApiResponceStatus<T>()
}