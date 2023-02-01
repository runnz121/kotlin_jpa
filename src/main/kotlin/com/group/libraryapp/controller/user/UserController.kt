package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

    // fun getUser(): List<UserResponse> = userService.getUsers() 이렇게도 쓸 수 있다
    @GetMapping("/user")
    fun getUser(): List<UserResponse>{
        return userService.getUsers()
    }

    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request)
    }

    // @RequestParam은 기본적으로 nullable 하지 않아야 동작한다 그런데 여기서 String? 이라고 쓰게되면
    // 스프링은 default 값이 false로 바뀜 따라서 requestparam시 null 값이 오지 않도록 써주는게 맞음
    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name)
    }
}