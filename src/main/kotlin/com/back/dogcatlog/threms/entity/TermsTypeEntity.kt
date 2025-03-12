package com.back.dogcatlog.threms.entity

import jakarta.persistence.*

@Entity
@Table(name = "terms_types")
data class TermsTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,


)
