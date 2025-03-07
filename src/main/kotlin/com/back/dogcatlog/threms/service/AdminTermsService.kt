package com.back.dogcatlog.threms.service

import com.back.dogcatlog.global.error.CustomException
import com.back.dogcatlog.global.error.ErrorCode
import com.back.dogcatlog.threms.dto.AddNewTermsVersion
import com.back.dogcatlog.threms.dto.AdminAddNewTermsType
import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity
import com.back.dogcatlog.threms.repository.TermsRepository
import com.back.dogcatlog.threms.repository.TermsTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional(readOnly = true)
class AdminTermsService(
    private val termsRepository: TermsRepository,
    private val termsTypeRepository: TermsTypeRepository
) {

    /**
     * 새 약관 추가하기 (ADMIN 전용)
     */
    @Transactional
    fun addNewTermsType(requestBody: AdminAddNewTermsType): Terms {
        val existingType = termsTypeRepository.findByName(requestBody.typeName)
        if (existingType != null) {
            throw CustomException(ErrorCode.TERMS_ALREADY_EXISTS)
        }

        // 새로운 TermsType 생성
        val newType = TermsTypeEntity(name = requestBody.typeName)
        termsTypeRepository.save(newType)

        // 중복 버전 체크
        if (termsRepository.findByTermsTypeAndVersion(newType, requestBody.version) != null) {
            throw CustomException(ErrorCode.TERMS_VERSION_ALREADY_EXISTS)
        }

        // 초기 Terms 생성
        val newTerms = Terms(
            termsType = newType,
            version = requestBody.version,
            isRequired = requestBody.isRequired,
            createdAt = Instant.now()  // 명시적 초기화 (엔티티의 @CreationTimestamp와 중복 가능)
        )
        return termsRepository.save(newTerms)
    }

    /**
     * 기존 약관에 새 버전 추가 (ADMIN 전용)
     */
    @Transactional
    fun addNewTermsVersion(requestBody: AddNewTermsVersion) {
        val termsType = termsTypeRepository.findByName(requestBody.typeName)
            ?: throw CustomException(ErrorCode.TERMS_NOT_FOUND)

        // 중복 버전 체크
        if (termsRepository.findByTermsTypeAndVersion(termsType, requestBody.version) != null) {
            throw CustomException(ErrorCode.TERMS_VERSION_ALREADY_EXISTS)
        }

        // 약관동의 필수 항목인 경우 전부 false로 바꿈
        if (requestBody.isRequired) {
            termsRepository.findByTermsType(termsType).forEach { term ->
                term.isRequired = false
                termsRepository.save(term)
            }
        }

        val newTerms = Terms(
            termsType = termsType,
            version = requestBody.version,
            isRequired = requestBody.isRequired,
            createdAt = Instant.now()  // 명시적 초기화
        )
        termsRepository.save(newTerms)
    }
}
