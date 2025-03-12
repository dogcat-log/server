package com.back.dogcatlog.threms.dto

import com.back.dogcatlog.threms.entity.Terms

data class AdminTermsList(
    val typeName: String,
    val version: Long,
    val isRequired: Boolean
) {
    companion object {
        fun of(termsList: Terms): AdminTermsList {
            return AdminTermsList(
                typeName = termsList.termsType.name,
                version = termsList.version,
                isRequired = termsList.isRequired
            )
        }
    }
}
