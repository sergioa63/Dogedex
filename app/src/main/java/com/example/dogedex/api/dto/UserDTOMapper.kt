package com.example.dogedex.api.dto

import com.example.dogedex.model.User


class UserDTOMapper {
    fun fromUserDTOToUserDomain (userDto: UserDTO) : User {
        return User(userDto.id, userDto.email, userDto.authenticationToken)
    }
}