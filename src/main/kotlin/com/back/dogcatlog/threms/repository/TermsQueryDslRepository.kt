package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.dto.TermsStatusResponse
import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity

interface TermsQueryDslRepository {
    // 타입의 최신버전
    fun findLatestByTermsType(termsType: TermsTypeEntity): Terms?

    // 필수 동의 여부
    fun findRequiredTerms(): List<Terms>

    // 같은 타입 조회
    fun findByTermsType(termsType: TermsTypeEntity): List<Terms>

    // 타입, 버전 같은게 있는지
    fun findByTermsTypeAndVersion(termType: TermsTypeEntity, version: Long): Terms?

    // 전체 약관동의 목록 조회
    fun findAllTerms(userId: Long): TermsStatusResponse
}
