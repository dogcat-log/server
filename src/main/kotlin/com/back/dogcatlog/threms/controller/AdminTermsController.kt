package com.back.dogcatlog.threms.controller


import com.back.dogcatlog.global.error.SuccessResponse
import com.back.dogcatlog.threms.dto.AddNewTermsVersion
import com.back.dogcatlog.threms.dto.AdminAddNewTermsType
import com.back.dogcatlog.threms.service.AdminTermsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/terms")
class AdminTermsController(
    private val adminTermsService: AdminTermsService
) {

    /**
     * 새 약관 종류와 초기 버전 추가 (ADMIN 용)
     * */
    @PostMapping("/types")
    fun addNewTermsType(
        @RequestBody requestBody: AdminAddNewTermsType,
    ): ResponseEntity<SuccessResponse<String>> {
        val newTerms = adminTermsService.addNewTermsType(requestBody)
        return ResponseEntity.ok(SuccessResponse.of("${newTerms.termsType}, ${newTerms.version}"))
    }


    /**
     * 기존 약관에 새 버전 추가 (ADMIN 전용)
     * */
    @PostMapping("/versions")
    fun addNewTermsVersion(
        @RequestBody requestBody: AddNewTermsVersion
    ): ResponseEntity<SuccessResponse<Boolean>> {
        adminTermsService.addNewTermsVersion(requestBody)
        return ResponseEntity.ok(SuccessResponse.of(true))
    }

}
