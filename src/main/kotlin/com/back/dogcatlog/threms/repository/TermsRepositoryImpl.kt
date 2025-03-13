package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.dto.TermsDetailResponse
import com.back.dogcatlog.threms.dto.TermsStatusResponse
import com.back.dogcatlog.threms.entity.QTerms
import com.back.dogcatlog.threms.entity.QUserTerms
import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory

class TermsRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : TermsQueryDslRepository {
    override fun findLatestByTermsType(termsType: TermsTypeEntity): Terms? {
        return jpaQueryFactory
            .selectFrom(QTerms.terms)
            .where(QTerms.terms.termsType.eq(termsType))
            .orderBy(QTerms.terms.version.desc())
            .limit(1)
            .fetchOne()
    }

    override fun findRequiredTerms(): List<Terms> {
        return jpaQueryFactory
            .selectFrom(QTerms.terms)
            .where(QTerms.terms.isRequired.eq(true))
            .fetch()
    }

    override fun findByTermsType(termsType: TermsTypeEntity): List<Terms> {
        return jpaQueryFactory
            .selectFrom(QTerms.terms)
            .where(QTerms.terms.termsType.eq(termsType))
            .fetch()
    }

    override fun findByTermsTypeAndVersion(termType: TermsTypeEntity, version: Long): Terms? {
        return jpaQueryFactory
            .selectFrom(QTerms.terms)
            .where(
                QTerms.terms.termsType.eq(termType),
                QTerms.terms.version.eq(version)
            )
            .fetchOne()
    }

    //
    override fun fetchTermsStatus(userId: Long): TermsStatusResponse {
        // 각 타입별 최신 버전을 먼저 찾기
        val latestVersions = jpaQueryFactory
            .select(
                QTerms.terms.termsType,
                QTerms.terms.version.max()
            )
            .from(QTerms.terms)
            .groupBy(QTerms.terms.termsType)
            .fetch()

        // 최신 버전의 약관들 조회
        val termsWithAgreements = jpaQueryFactory
            .select(
                Projections.constructor(
                    TermsDetailResponse::class.java,
                    QTerms.terms.termsType,
                    QTerms.terms.version,
                    QTerms.terms.isRequired,
                    QUserTerms.userTerms.isAgreed.coalesce(false),
                    QTerms.terms.descriptionUrl
                )
            )
            .from(QTerms.terms)
            .leftJoin(QUserTerms.userTerms)
            .on(
                QUserTerms.userTerms.user.id.eq(userId)
                    .and(QTerms.terms.eq(QUserTerms.userTerms.terms))
            )
            .where(
                latestVersions.map { version ->
                    QTerms.terms.termsType.eq(version.get(QTerms.terms.termsType))
                        .and(QTerms.terms.version.eq(version.get(QTerms.terms.version.max())))
                }.reduce { acc, predicate -> acc.or(predicate) }
            )
            .fetch()

        val needsAgreement = termsWithAgreements.any {
            it.isRequired && !it.isAgreed
        }

        return TermsStatusResponse(
            needsAgreement = needsAgreement,
            terms = termsWithAgreements
        )
    }

}
