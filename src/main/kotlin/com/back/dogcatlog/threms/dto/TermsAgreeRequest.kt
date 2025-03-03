package com.back.dogcatlog.threms.dto

import com.back.dogcatlog.threms.TermsType


class TermsAgreeRequest(
    val agreements: List<TermsAgreement>
)

data class TermsAgreement(
    val termsType: TermsType,
    val isAgreed: Boolean
)
