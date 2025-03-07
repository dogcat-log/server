package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity

interface TermsQueryDslRepository {
    fun findLatestByTermsType(termsType: TermsTypeEntity): Terms?
    fun findRequiredTerms(): List<Terms>
    fun findByTermsType(termsType: TermsTypeEntity): List<Terms>
    fun findByTermsTypeAndVersion(termType: TermsTypeEntity, version: String): Terms?
}
