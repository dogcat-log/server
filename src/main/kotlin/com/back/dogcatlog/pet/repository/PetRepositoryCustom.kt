package com.back.dogcatlog.pet.repository

import com.back.dogcatlog.pet.entity.Pet

interface PetRepositoryCustom {
    fun findByOwner(ownerId: Long): List<Pet>
}
