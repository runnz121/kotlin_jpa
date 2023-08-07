package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.Query

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : UserRepositoryCustom {

    override fun findAllWithHistories(): List<User> {
        // n 개의 같은 유저가 나올수 있음으로 distinct
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory).on(userLoanHistory.user.id.eq(user.id)).fetchJoin() // 앞의 join을 fetchJoin으로 한다
            .fetch()
    }

    // 위의 querydsl이 아래 문을 대체
//    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories") // distinct 를 추가해야 같은 유저에 대해선 하나만 적용 , fetch 조인으로 N+1 해결
//    fun findAllWithHistories() : List<User>
}