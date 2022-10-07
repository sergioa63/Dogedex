package com.example.dogedex.api

sealed class ApiResponceStatus<T> (){
    class Success<T>(val data : T) : ApiResponceStatus<T>()
    class Error<T>(val msn : Int) : ApiResponceStatus<T>()
    class Loading<T>() : ApiResponceStatus<T>()
}