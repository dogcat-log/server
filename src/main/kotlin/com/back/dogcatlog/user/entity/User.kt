package com.back.dogcatlog.user.entity

import com.back.dogcatlog.user.dto.AuthProvider
import com.back.dogcatlog.threms.TermsVersion
import com.back.dogcatlog.user.dto.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val userEmail: String,

    @Column(nullable = false)
    var userPassword: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val provider: AuthProvider,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val userRole: UserRole = UserRole.USER,

    @Column(length = 10)
    var latestTermVersion: TermsVersion? = null,

    @CreationTimestamp
    val createAt: Instant? = null,

    @UpdateTimestamp
    var updatedAt: Instant? = null,

    )
