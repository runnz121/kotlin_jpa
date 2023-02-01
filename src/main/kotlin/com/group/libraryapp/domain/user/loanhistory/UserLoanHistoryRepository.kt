package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    // nullable value return
    fun findByBookNameAndIsReturn(bookName :String, isReturn :Boolean) : UserLoanHistory?
}