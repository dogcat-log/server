package com.back.dogcatlog.threms

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "user_terms")
data class UserTerms(
    @EmbeddedId
    val id: UserTermsId,

    @Column(nullable = false)
    val isAgreed: Boolean,

    @Column(nullable = false)
    val agreedAt: Instant = Instant.now()
)

@Embeddable
data class UserTermsId(
    val userId: Long,
    val version: TermsVersion
)
