package com.back.dogcatlog.threms.service

import com.back.dogcatlog.config.CustomUserDetails
import com.back.dogcatlog.global.error.CustomException
import com.back.dogcatlog.global.error.ErrorCode
import com.back.dogcatlog.threms.dto.TermsAgreeRequest
import com.back.dogcatlog.threms.entity.UserTerms
import com.back.dogcatlog.threms.repository.TermsRepository
import com.back.dogcatlog.threms.repository.TermsTypeRepository
import com.back.dogcatlog.threms.repository.UserTermsRepository
import com.back.dogcatlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional(readOnly = true)
class UserTermsService(
    private val userRepository: UserRepository,
    private val termsRepository: TermsRepository,
    private val userTermsRepository: UserTermsRepository,
    private val termsTypeRepository: TermsTypeRepository
) {

    /**
     * 사용자 약관 등록 (USER 전용)
     */
    @Transactional
    fun registerTermsAgreement(request: TermsAgreeRequest, userDetails: CustomUserDetails) {
        val user = userRepository.findById(userDetails.getId())
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }

        request.agreements.forEach { agreement ->
            val termsType = termsTypeRepository.findByName(agreement.termsTypeName)
                ?: throw CustomException(ErrorCode.TERMS_NOT_FOUND)
            val latestTerms = termsRepository.findLatestByTermsType(termsType)
                ?: throw CustomException(ErrorCode.TERMS_NOT_FOUND)

            val userTerms = user.agreedTerms.find { it.terms == latestTerms }
                ?.apply {
                    isAgreed = agreement.isAgreed
                    agreedAt = Instant.now()
                } ?: UserTerms(
                user = user,
                terms = latestTerms,
                isAgreed = agreement.isAgreed,
                agreedAt = Instant.now()
            )
            userTermsRepository.save(userTerms)
        }
    }

    /**
     * 필수 약관 동의 여부 확인 (USER 전용)
     */
    fun checkRequiredTermsAgreement(userId: Long): Boolean {
        val user = userRepository.findById(userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }
        val requiredTerms = termsRepository.findRequiredTerms()

        return requiredTerms.all { required ->
            user.agreedTerms.any { it.terms == required && it.isAgreed }
        }
    }
}
