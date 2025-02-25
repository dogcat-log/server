package com.back.dogcatlog.pet.dto

import com.back.dogcatlog.pet.entity.Pet
import com.back.dogcatlog.user.entity.User
import java.time.Instant

data class PetRequest(
    val petName: String,
    val petType: String,
    val breed: String,
    val birthDate: Instant
) {
    fun toEntity(owner: User): Pet {
        return Pet(
            petName = petName,
            petType = petType,
            breed = breed,
            petBirthDate = birthDate,
            owner = owner.id,
        )
    }
}
