package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository: JpaRepository<User, Long> {

    fun findByName(name: String): User? // Optional<User> -> optional 대신 물음표를 통해 nullable한 값을 표현

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories") // distinct 를 추가해야 같은 유저에 대해선 하나만 적용 , fetch 조인으로 N+1 해결 
    fun findAllWithHistories() : List<User>
}