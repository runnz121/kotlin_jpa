package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService (
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
){

    @Transactional
    fun saveBook(request: BookRequest) {
        val book = Book(request.name, request.type)
        bookRepository.save(book)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryRepository.findByBookNameAndStatus(request.bookName,UserLoanStatus.LOANED) != null) {
              throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }
        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanBook(): Int {
        return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOANED).toInt()
        //return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse>  {
        return bookRepository.getStats()

//        return bookRepository.findAll() // List<Book>
//            .groupBy { book -> book.type } // MAP<BookType, List<Book>>
//            .map { (type, books) -> BookStatResponse(type, books.size) } //List<BookStatResponse>

//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books) {
//            // .? 는 앞의 값이 NULL이 아닌 경우메낫 ㅣㄹ행
//            results.firstOrNull{ dto -> book.type == dto.type}?.plusOne()
//                // 엘비스 연산자 : NULL 인 경우에
//                ?: results.add(BookStatResponse(book.type, 1))
//        }
//        return results
    }
}