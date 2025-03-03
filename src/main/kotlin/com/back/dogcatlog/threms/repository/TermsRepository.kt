package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.UserTerms
import org.springframework.data.jpa.repository.JpaRepository

interface TermsRepository: JpaRepository<UserTerms, Long>{
}
