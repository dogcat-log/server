package com.back.dogcatlog.threms.dto


data class TermsStatusResponse(
    val needsAgreement: Boolean,
    val terms: List<TermsDetailResponse>
)
