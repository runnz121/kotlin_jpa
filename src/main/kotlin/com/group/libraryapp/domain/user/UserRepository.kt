package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {

    fun findByName(name: String): User? // Optional<User> -> optional 대신 물음표를 통해 nullable한 값을 표현
}