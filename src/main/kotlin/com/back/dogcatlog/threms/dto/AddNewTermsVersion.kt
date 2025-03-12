package com.back.dogcatlog.threms.dto

import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity

data class AddNewTermsVersion(
    val typeName: String,
    val version: Long,
    val isRequired: Boolean,
    val descriptionUrl: String
) {
    fun toEntity(termsType: TermsTypeEntity): Terms {
        return Terms(
            termsType = termsType,
            version = version,
            isRequired = isRequired,
            descriptionUrl = descriptionUrl,
        )
    }
}
