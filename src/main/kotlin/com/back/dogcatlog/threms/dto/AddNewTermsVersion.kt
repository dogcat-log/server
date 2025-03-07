package com.back.dogcatlog.threms.dto

data class AddNewTermsVersion(
    val typeName: String,
    val version: String,
    val isRequired: Boolean
)
