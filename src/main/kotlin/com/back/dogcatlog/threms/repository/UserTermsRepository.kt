package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.entity.UserTerms
import org.springframework.data.jpa.repository.JpaRepository

interface UserTermsRepository: JpaRepository<UserTerms, Long> {
}
