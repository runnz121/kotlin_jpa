package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor( // 각각 작성해야할 @Autowired 생략 할 수 있다.
     private val userRepository: UserRepository,
     private val userService: UserService,
) {
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장 동작")
    fun saveUserTest() {
        //given
        val request = UserCreateRequest("박종빈", null)

        //when
        userService.saveUser(request)

        //then
        val results = userRepository.findAll()
        assertThat(results).hasSize((1))
        assertThat(results[0].name).isEqualTo("박종빈")
        assertThat(results[0].age).isNull() //kotlin은 java의 Inteager가 null이 들어올 수 있는지 모름 따라서 getAge에 @Nullable 을 붙여 주어 null이 올 수 있음을 알려준다
    }

    @Test
    @DisplayName("유저 조회 동작")
    fun getUsersTest() {

        //given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))

        //when
        val results = userService.getUsers()

        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")  // User의 name 필드를 갖고와서 확인
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20,null)  // User의 name 필드를 갖고와서 확인
    }


    @Test
    @DisplayName("유저 업데이트 동작")
    fun updateUserNameTest() {
        //given
        val saveUser = userRepository.save(User("A", null))
        // kotlin은 nullable 값이 이지만 userudpaterequest는 null 아니다
        // 하디만 한번 값이 저장되면 절대로 null이 아님으로 !!로 null이 아님을 단언함
        val request = UserUpdateRequest(saveUser.id!!, "B")

        //when
        userService.updateUserName(request)

        //then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제 동작")
    fun deleteUserTest() {
        //given
        userRepository.save(User("A", null))

        //when
        userService.deleteUser("A")

        //then
        assertThat(userRepository.findAll()).isEmpty()
    }
}