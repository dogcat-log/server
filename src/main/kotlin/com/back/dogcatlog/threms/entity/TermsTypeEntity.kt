package com.back.dogcatlog.threms.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "terms_types")
data class TermsTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @CreationTimestamp
    val createdAt: Instant? = null,
)
