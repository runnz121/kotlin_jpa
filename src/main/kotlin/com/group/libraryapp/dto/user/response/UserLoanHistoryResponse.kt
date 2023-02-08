package com.group.libraryapp.dto.user.response

data class UserLoanHistoryResponse (
    val name: String, // 유저이름
    val books: List<BookHistoryResponse>
)


data class BookHistoryResponse(
    val name: String,
    val isReturn: Boolean,
)