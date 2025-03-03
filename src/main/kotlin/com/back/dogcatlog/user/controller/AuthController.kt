package com.back.dogcatlog.user.controller

import com.back.dogcatlog.global.error.SuccessResponse
import com.back.dogcatlog.user.dto.TokenResponse
import com.back.dogcatlog.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login/oauth2")
    fun oauthLogin(
        @RequestParam accessToken: String,
        @RequestParam provider: String
    ): ResponseEntity<SuccessResponse<TokenResponse>> {
        val tokens = authService.authenticateWithOAuth2(accessToken, provider)
        return ResponseEntity.ok(SuccessResponse.of(tokens))
    }

    @PostMapping("/refresh")
    fun refreshToken(
        @RequestParam refreshToken: String
    ): ResponseEntity<TokenResponse> {
        val tokens = authService.refreshToken(refreshToken)
        return ResponseEntity.ok(tokens)
    }




}
