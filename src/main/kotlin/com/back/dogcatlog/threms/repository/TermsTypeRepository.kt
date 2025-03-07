package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.entity.TermsTypeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TermsTypeRepository : JpaRepository<TermsTypeEntity, Long> {
    fun findByName(typeName: String) : TermsTypeEntity?
}
