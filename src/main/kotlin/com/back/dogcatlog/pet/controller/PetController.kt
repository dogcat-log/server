package com.back.dogcatlog.pet.controller

import com.back.dogcatlog.config.CustomUserDetails
import com.back.dogcatlog.global.error.SuccessResponse
import com.back.dogcatlog.pet.dto.PetResponse
import com.back.dogcatlog.pet.service.PetService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class PetController(
    private val petService: PetService,
) {
    @GetMapping("/pets")
    fun getPets(@AuthenticationPrincipal user: CustomUserDetails):
            ResponseEntity<SuccessResponse<List<PetResponse>>> {
        val pets = petService.petList(user)
        return ResponseEntity.ok(SuccessResponse.of(pets))
    }

}
