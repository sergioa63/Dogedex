package com.example.dogedex.api.responces

import com.example.dogedex.api.DogListResponse
import com.squareup.moshi.Json

class DogListApiResponse(
    val message: String,
    @field:Json(name = "is_success") val isSuccess: Boolean,
    val data: DogListResponse,
)