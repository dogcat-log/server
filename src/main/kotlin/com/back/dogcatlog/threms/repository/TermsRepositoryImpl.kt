package com.back.dogcatlog.threms.repository

import com.back.dogcatlog.threms.entity.QTerms
import com.back.dogcatlog.threms.entity.Terms
import com.back.dogcatlog.threms.entity.TermsTypeEntity
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

    override fun findByTermsTypeAndVersion(termType: TermsTypeEntity, version: String): Terms? {
        return jpaQueryFactory
            .selectFrom(QTerms.terms)
            .where(
                QTerms.terms.termsType.eq(termType),
                QTerms.terms.version.eq(version)
            )
            .fetchOne()

    }


}
