package com.example.dogedex.auth

import com.example.dogedex.api.ApiResponceStatus
import com.example.dogedex.api.DogsApi
import com.example.dogedex.api.dto.LoginDTO
import com.example.dogedex.api.dto.SingUpDTO
import com.example.dogedex.api.dto.UserDTOMapper
import com.example.dogedex.api.makeNetworkCall
import com.example.dogedex.model.User
import java.lang.Exception

class AuthRepository {

    suspend fun singUp(email: String, password : String, passwordConfirmation : String)
        : ApiResponceStatus<User> {
        return makeNetworkCall {
            val singUpDTO = SingUpDTO(email, password, passwordConfirmation)
            val singUpResponse = DogsApi.retrofitService.singUp(singUpDTO)
            if(!singUpResponse.isSuccess) {
                throw Exception(singUpResponse.message)
            }
            val userDTO = singUpResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }

    suspend fun login(email: String, password : String)
            : ApiResponceStatus<User> {
        return makeNetworkCall {
            val loginDTO = LoginDTO(email, password)
            val loginResponse = DogsApi.retrofitService.login(loginDTO)
            if(!loginResponse.isSuccess) {
                throw Exception(loginResponse.message)
            }
            val userDTO = loginResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }
}