package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {
    throw IllegalArgumentException()
}

// <Entity Type, ID 값> id를 받고, T를 반환
// CrudRepository interface를 원본으로 하여 마치 findByIdorThrow가 해당 인터페이스에 정의되어있는 것처럼 쓸 수 있게 만들어줌
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    // this -> CrudRepository 의 메서드를 사용하고 우리가 정의한 fail로 에러를 반환함
    // 즉 CrudRepositoryExtensions.kt의 메서드를 한번더 감쌈
    return this.findByIdOrNull(id) ?: fail()
}