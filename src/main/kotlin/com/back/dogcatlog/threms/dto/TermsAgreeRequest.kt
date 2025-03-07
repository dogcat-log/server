package com.back.dogcatlog.threms.dto


class TermsAgreeRequest(
    val agreements: List<TermsAgreement>
)

data class TermsAgreement(
    val termsTypeName: String,
    val isAgreed: Boolean
)
