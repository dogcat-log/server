package com.back.dogcatlog.user.controller

import com.back.dogcatlog.config.CustomUserDetails
import com.back.dogcatlog.global.error.SuccessResponse
import com.back.dogcatlog.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userRepository: UserRepository,
) {

    @GetMapping("/profile")
    fun getUser(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ): ResponseEntity<SuccessResponse<String>> {
        val userUid = userRepository.findById(userDetails.getId())
        return ResponseEntity.ok(SuccessResponse.of(userUid.get().userEmail))

    }
}
