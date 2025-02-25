package com.back.dogcatlog.pet.repository

import com.back.dogcatlog.pet.entity.Pet
import com.back.dogcatlog.pet.entity.QPet
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository


@Repository
class PetRepositoryImpl(
    private val queryFactory: JPAQueryFactory

) : PetRepositoryCustom {
    private val pet = QPet.pet

    override fun findByOwner(ownerId: Long): List<Pet> {
        return queryFactory
            .selectFrom(pet)
            .where(pet.owner.eq(ownerId))
            .orderBy(pet.createdAt.desc())
            .fetch()
    }
}
