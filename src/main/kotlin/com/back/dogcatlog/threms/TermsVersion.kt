package com.back.dogcatlog.threms

enum class TermsVersion(val version: String) {
    V1("v1.0");

    companion object {
        fun latest(): TermsVersion = entries.last()
    }
}
