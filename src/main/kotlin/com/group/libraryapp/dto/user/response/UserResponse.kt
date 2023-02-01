package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

// DTO 는 equals and hashcode, ToString을 구현하는 data클래스로 바꿔주는 것이 좋다
data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?
) {
    // 부생성자 생성 -> this는 주 생성자를 말함 (UseResponse)
//    constructor(user: User): this (
//        id = user.id!!,
//        name = user.name,
//        age = user.age!!
//    )

    // 정적 팩토리 메서드를 활용하여 객체 생성
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age!!
            )
        }
    }
}