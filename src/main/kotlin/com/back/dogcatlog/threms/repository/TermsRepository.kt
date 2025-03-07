package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.entity.Terms
import org.springframework.data.jpa.repository.JpaRepository

interface TermsRepository: JpaRepository<Terms, Long>, TermsQueryDslRepository {
}
