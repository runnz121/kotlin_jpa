package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.BookHistoryResponse
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
//open class -> @Transactional을 쓸려면 해당 함수와 클래스가 모두 상속이 가능해야된다
//하지만 코틀린은 기본적으로 상속 불가(final) 임으로 open을 각각 붙여주어야 하는데, 이게 귀찮으니 plugin 을 추가해준다
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional
    //open fun
    fun saveUser(request: UserCreateRequest) {
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }
    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map{user -> UserResponse.of(user)} //.map (::UserResponse) 에서 정적 팩토리 메서드로 구현시
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        // 기존의 jpa crudrepository에서 제공하는 findbyId는 기본 반환값이 optional이다
        //val user = userRepository.findById(request.id).orElseThrow(::IllegalArgumentException)

        // 하지만 코틀린용 CrudRepositoryExtensions.kt에 있는 findByIdOrNull을 사용하면 이를 ? 와 같이 처리할 수 있다.
        //val user = userRepository.findByIdOrNull(request.id)?: fail()

        // 위의 메서드를 한번더 감싸 커스텀 익셉션을 발생시킴
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(user.name)
    }

    @Transactional
    fun deleteUser(name: String) {                  //util 패키지에 있는 exception을 처리하는 함수를 공통 호출 // 엘비스 연산자를 통해 null 인 경우 익셉션 발생시킴(optional 반환을 ? 로 바꿨기 때문)
        val user = userRepository.findByName(name) ?: fail()//?: throw IllegalArgumentException() //.orElseThrow(::IllegalArgumentException)
        userRepository.delete(user)
    }

    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userRepository.findAll().map { user ->
            UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map { history ->
                    BookHistoryResponse(
                        name = history.bookName,
                        isReturn = history.status == UserLoanStatus.RETURNED
                    )
                }
            )
        }
    }

}