package com.back.dogcatlog.threms.entity

import com.back.dogcatlog.user.entity.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "user_terms")
data class UserTerms(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "terms_id")
    val terms: Terms,

    @Column(nullable = false)
    var isAgreed: Boolean,

    @Column(nullable = false)
    var agreedAt: Instant,

)
