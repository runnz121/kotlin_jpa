package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component

@Component
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun getStats(): List<BookStatResponse> {
        return queryFactory.select(
            // 주어진 dto 생성자를 호출
            Projections.constructor(
            BookStatResponse::class.java,
            book.type,
            book.id.count(),
        ))
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
//
//    // 패키지를 전체 갖고와서 NEW 로 객체를 생성시킬 수 있음
//    @Query("SELECT NEW com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) " +
//            " FROM Book b GROUP BY b.type")
//    fun getStats(): List<BookStatResponse>
}