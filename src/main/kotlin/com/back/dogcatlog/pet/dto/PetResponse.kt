package com.back.dogcatlog.pet.dto

import com.back.dogcatlog.pet.entity.Pet
import java.time.Instant


data class PetResponse(
    val id: Long,
    val petName: String,
    val petType: String,
    val breed: String?,
    val petBirthDate: Instant?,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun from(pet: Pet): PetResponse {
            return PetResponse(
                id = pet.id!!,
                petName = pet.petName,
                petType = pet.petType,
                breed = pet.breed,
                petBirthDate = pet.petBirthDate,
                createdAt = pet.createdAt,
                updatedAt = pet.updatedAt
            )
        }
    }
}
