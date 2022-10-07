package com.example.dogedex.api.dto

import com.squareup.moshi.Json

data class SingUpDTO (
    val email: String,
    val password : String,
    @field:Json(name = "password_confirmation") val passwordConfirmation : String
)