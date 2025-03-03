package com.back.dogcatlog.threms.service

import com.back.dogcatlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping


@Service
@Transactional(readOnly = true)
class TermsService(
    private val userRepository: UserRepository,

) {
    @Transactional
    fun agreeToTerms()

}
