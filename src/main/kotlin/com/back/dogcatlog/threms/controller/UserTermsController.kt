package com.back.dogcatlog.threms.controller

import com.back.dogcatlog.config.CustomUserDetails
import com.back.dogcatlog.global.error.SuccessResponse
import com.back.dogcatlog.threms.dto.TermsAgreeRequest
import com.back.dogcatlog.threms.dto.TermsStatusResponse
import com.back.dogcatlog.threms.service.UserTermsService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/terms")
class UserTermsController(
    private val userTermsService: UserTermsService
) {
    /**
     * 사용자 약관 동의 등록 (USER 전용)
     */
    @PostMapping("/agree")
    fun registerTermsAgreement(
        @RequestBody request: TermsAgreeRequest,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ): ResponseEntity<SuccessResponse<Boolean>> {

        userTermsService.registerTermsAgreement(
            request = request,
            userDetails = userDetails
        )
        return ResponseEntity.ok(SuccessResponse.of(true))
    }

    /**
     * 필수 약관 동의 여부 확인 (USER 전용)
     */
    @GetMapping("/check-required")
    fun checkRequiredTermsAgreement(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ): ResponseEntity<SuccessResponse<TermsStatusResponse>> {
        val termsStatue = userTermsService.checkRequiredTermsAgreements(userDetails)
        return ResponseEntity.ok(SuccessResponse.of(termsStatue))
    }
}
