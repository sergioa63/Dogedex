package com.example.dogedex.api.responces

import com.squareup.moshi.Json

class AuthApiResponce
    (val message: String,
     @field:Json(name = "is_success") val isSuccess: Boolean,
     val data: UserResponce
)