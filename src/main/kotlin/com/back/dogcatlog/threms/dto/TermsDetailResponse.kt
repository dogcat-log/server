package com.back.dogcatlog.threms.dto

import com.back.dogcatlog.threms.entity.TermsTypeEntity

data class TermsDetailResponse(
    val termsType: TermsTypeEntity,
    val version: Long,
    val isRequired: Boolean,
    val isAgreed: Boolean,
    val descriptionUrl: String
)
