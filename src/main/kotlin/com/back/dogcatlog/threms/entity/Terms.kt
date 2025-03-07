package com.back.dogcatlog.threms.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant


@Entity
@Table(name = "terms")
data class Terms(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "terms_type_id")
    val termsType: TermsTypeEntity,

    @Column(nullable = false)
    val version: String,

    @Column(nullable = false)
    var isRequired: Boolean,


    @CreationTimestamp
    val createdAt: Instant? = null,
)
