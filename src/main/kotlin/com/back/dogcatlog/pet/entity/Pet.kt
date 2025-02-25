package com.back.dogcatlog.pet.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "pets")
data class Pet(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val owner: Long,

    @Column(nullable = false)
    var petName: String,

    @Column(nullable = false)
    var petType: String,

    var breed: String? = null,

    var petBirthDate: Instant? = null,

    val createdAt: Instant = Instant.now(),

    var updatedAt: Instant = Instant.now(),

)
