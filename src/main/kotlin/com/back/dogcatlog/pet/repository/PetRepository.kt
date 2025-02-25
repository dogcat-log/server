package com.back.dogcatlog.pet.repository

import com.back.dogcatlog.pet.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long>, PetRepositoryCustom
