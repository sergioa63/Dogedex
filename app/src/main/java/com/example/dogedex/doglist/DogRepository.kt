package com.example.dogedex.doglist

import com.example.dogedex.model.Dog
import com.example.dogedex.api.ApiResponceStatus
import com.example.dogedex.api.DogsApi.retrofitService
import com.example.dogedex.api.dto.DogDTOMapper
import com.example.dogedex.api.makeNetworkCall as makeNetworkCall

class DogRepository {
    suspend fun downloadDogs() : ApiResponceStatus<List<Dog>> {
        return makeNetworkCall {
            val dogListApiResponse = retrofitService.getAllDogs()
            val docDtoList = dogListApiResponse.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(docDtoList)
        }
    }

    private fun getFakeDogs(): MutableList<Dog> {
        val dogList = mutableListOf<Dog>()
        dogList.add(
            Dog(
                1, 1, "Chihuahua", "Chihuahua", "Toy",
                "19", "Brave", "12 - 15",
                "2.0", "2.5", "12.0"
            )
        )
        dogList.add(
            Dog(
                2, 2, "Pug", "Pug", "Toy",
                "12", "Friendly", "www.pug.com",
                "10 - 12", "4.5", "12.0"
            )
        )
        dogList.add(
            Dog(
                3, 3, "Husky", "Husky", "Sporting",
                "15", "www.husky.com", "8 - 12 ",
                "5.0", "4.5", "12.0"
            )
        )
        return dogList
    }
}