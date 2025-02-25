package com.back.dogcatlog.pet.service

import com.back.dogcatlog.config.CustomUserDetails
import com.back.dogcatlog.global.error.CustomException
import com.back.dogcatlog.global.error.ErrorCode
import com.back.dogcatlog.pet.dto.PetRequest
import com.back.dogcatlog.pet.dto.PetResponse
import com.back.dogcatlog.pet.repository.PetRepository
import com.back.dogcatlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PetService(
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun createPet(user: CustomUserDetails, request: PetRequest) {
        val owner = userRepository.findById(user.getId())
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        val pet = request.toEntity(owner)
        petRepository.save(pet)
    }

    fun petList(user:CustomUserDetails): List<PetResponse> {
        return petRepository.findByOwner(user.getId())
            .map { PetResponse.from(it) }
    }

}
