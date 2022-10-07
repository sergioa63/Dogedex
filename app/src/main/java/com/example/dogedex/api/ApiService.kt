package com.example.dogedex.api

import com.example.dogedex.BASE_URL
import com.example.dogedex.GET_ALL_DOGS_URL
import com.example.dogedex.SIGN_IN_URL
import com.example.dogedex.SIGN_UP_URL
import com.example.dogedex.api.dto.LoginDTO
import com.example.dogedex.api.dto.SingUpDTO
import com.example.dogedex.api.responces.DogListApiResponse
import com.example.dogedex.api.responces.AuthApiResponce
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService{
    @GET(GET_ALL_DOGS_URL)
    suspend fun getAllDogs(): DogListApiResponse

    @POST(SIGN_UP_URL)
    suspend fun singUp(@Body singUpDTO : SingUpDTO) : AuthApiResponce

    @POST(SIGN_IN_URL)
    suspend fun login(@Body singUpDTO : LoginDTO) : AuthApiResponce
}

object DogsApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}