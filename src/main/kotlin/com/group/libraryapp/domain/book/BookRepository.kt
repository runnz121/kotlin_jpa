package com.group.libraryapp.domain.book

import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BookRepository : JpaRepository<Book, Long> {

    fun findByName(bookName: String): Book?


    // 패키지를 전체 갖고와서 NEW 로 객체를 생성시킬 수 있음
    @Query("SELECT NEW com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) " +
            " FROM Book b GROUP BY b.type")
    fun getStats(): List<BookStatResponse>

}