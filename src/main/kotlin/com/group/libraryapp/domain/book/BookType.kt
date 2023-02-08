package com.group.libraryapp.domain.book

// 요청 검증을 위한 enum 타입 생성
// 추가로 enum 값을 설정하지 않으면 BookType의 인덱스 값으로 들어간다
enum class BookType {
    COMPUTER,
    ECONOMY,
    SOCIETY,
    LANGUAGE,
    SCIENCE,
}