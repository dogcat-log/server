package com.back.dogcatlog.user.repository

import com.back.dogcatlog.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserEmail(email: String): User?
}
