package com.back.dogcatlog.user.entity

import com.back.dogcatlog.user.dto.AuthProvider
import com.back.dogcatlog.user.dto.UserRole
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val userEmail: String,

    @Column(nullable = false)
    var userPassword: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val provider: AuthProvider,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val userRole: UserRole = UserRole.USER,

    val createAt: Instant = Instant.now(),

    var updatedAt: Instant = Instant.now(),

    )
