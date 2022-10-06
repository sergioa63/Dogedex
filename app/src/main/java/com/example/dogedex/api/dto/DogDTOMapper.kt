package com.example.dogedex.api.dto

import com.example.dogedex.Dog

class DogDTOMapper {
    private fun fromDogDTOToDogDomain (dogDTO: DogDTO) : Dog {
        return Dog(dogDTO.id,dogDTO.index,dogDTO.name,dogDTO.type,dogDTO.heightFemale,dogDTO.heightMale,
        dogDTO.imageUrl,dogDTO.lifeExpectancy,dogDTO.temperament,dogDTO.weightFemale,dogDTO.weightMale)
    }
    fun fromDogDTOListToDogDomainList (dogDTOlist : List<DogDTO>) : List<Dog> {
        return dogDTOlist.map { fromDogDTOToDogDomain(it) }
    }
}