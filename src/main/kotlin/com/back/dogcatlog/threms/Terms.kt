package com.back.dogcatlog.threms

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant


@Entity
@Table(name = "terms")
data class Terms(
    @Id
    @Column(nullable = false)
    val version: TermsVersion,  // "v1.0", "v2.0"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val termsType: TermsType,

    @Column(nullable = false)
    val isRequired: Boolean,

    @CreationTimestamp
    val createdAt: Instant? = null
)
