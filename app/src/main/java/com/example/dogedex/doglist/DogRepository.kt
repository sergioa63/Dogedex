package com.example.dogedex.doglist

import com.example.dogedex.Dog
import com.example.dogedex.api.DogsApi.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository {
    suspend fun downloadDogs() : List<Dog> {
        return withContext(Dispatchers.IO) {
            val dogListApiResponse = retrofitService.getAllDogs()
            dogListApiResponse?.let {
                it.data.dogs
            }
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